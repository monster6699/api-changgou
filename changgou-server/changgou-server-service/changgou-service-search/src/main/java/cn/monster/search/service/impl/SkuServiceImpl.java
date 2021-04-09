package cn.monster.search.service.impl;

import cn.monster.content.feign.SkuFeign;
import cn.monster.content.pojo.Sku;
import cn.monster.entity.Result;
import cn.monster.search.dao.SkuEsMapper;
import cn.monster.search.pojo.SkuInfo;
import cn.monster.search.service.SkuService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired(required = false)
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired(required = false)
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Override
    public void importSku(){
        //调用changgou-service-goods微服务
        System.out.println(skuFeign);
        Result<List<Sku>> skuListResult = skuFeign.findByStatus("1");
        //将数据转成search.Sku
        List<SkuInfo> skuInfos=  JSON.parseArray(JSON.toJSONString(skuListResult.getData()),SkuInfo.class);
        for(SkuInfo skuInfo:skuInfos){
            Map<String, Object> specMap= JSON.parseObject(skuInfo.getSpec()) ;
            skuInfo.setSpecMap(specMap);
        }
        skuEsMapper.saveAll(skuInfos);
    }

    @Override
    public Map searchSku(Map<String, String> searchData) {
        String keyWords = searchData.get("keyWords");
        if(StringUtil.isEmpty(keyWords)) {
            keyWords = "华为";
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategoryGroup").field("categoryName").size(50));
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keyWords));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        SearchHits<SkuInfo> search = elasticsearchRestTemplate.search(query, SkuInfo.class);
        //6.返回结果
//        Aggregations aggregations = search.getAggregations();
//        Aggregation skuCategoryGroup = aggregations.get("skuCategoryGroup");
//        System.out.println(skuCategoryGroup);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SkuInfo> collect = search.getSearchHits().stream().map(info -> info.getContent()).collect(Collectors.toList());
        resultMap.put("rows", collect);
        resultMap.put("total", search.getTotalHits());
        return  resultMap;
    }
}

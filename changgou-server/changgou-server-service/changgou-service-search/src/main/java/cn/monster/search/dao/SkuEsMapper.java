package cn.monster.search.dao;

import cn.monster.content.pojo.Sku;
import cn.monster.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
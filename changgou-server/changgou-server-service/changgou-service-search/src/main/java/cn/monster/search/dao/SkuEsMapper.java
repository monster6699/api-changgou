package cn.monster.search.dao;

import cn.monster.content.pojo.Sku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuEsMapper extends ElasticsearchRepository<Sku,Long> {
}
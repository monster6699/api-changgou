package cn.monster.search.service;

import java.util.Map;

public interface SkuService {
    void importSku();
    Map searchSku(Map<String, String> searchData);
}

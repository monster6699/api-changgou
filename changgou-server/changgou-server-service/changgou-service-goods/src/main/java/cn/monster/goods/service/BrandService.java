package cn.monster.goods.service;

import cn.monster.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    /***
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /***
     * 根据id查询品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    void addBrand(Brand brand);

    void updateBrand(Brand brand);

    void deleteBrand(Integer id);

    List<Brand> findByBrandLIst(Brand brand);

    PageInfo<Brand> findByPageList(int page, int size);

    PageInfo<Brand> findByPageSelectList(Brand brand, int page, int size);
}

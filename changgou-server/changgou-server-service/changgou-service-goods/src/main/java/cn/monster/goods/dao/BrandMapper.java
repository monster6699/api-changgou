package cn.monster.goods.dao;
import cn.monster.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {

    @Select("SELECT tb.* FROM `tb_category_brand` tcb, tb_brand tb where tcb.category_id = #{categoryId} and tcb.brand_id = tb.id")
    List<Brand> findBrandByCategory(Integer categoryId);
}


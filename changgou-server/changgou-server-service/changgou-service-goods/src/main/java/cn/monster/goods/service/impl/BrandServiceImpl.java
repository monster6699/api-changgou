package cn.monster.goods.service.impl;

import cn.monster.goods.dao.BrandMapper;
import cn.monster.goods.pojo.Brand;
import cn.monster.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired(required = false)
    private BrandMapper brandMapper;

    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findByBrandLIst(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findByPageList(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Brand>(brandMapper.selectAll());
    }

    @Override
    public PageInfo<Brand> findByPageSelectList(Brand brand, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(brand);
        return new PageInfo<Brand>(brandMapper.selectByExample(example));
    }

    private Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (!StringUtils.isEmpty(brand.getImage())) {
                criteria.andLike("image", "%" + brand.getImage() + "%");
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andLike("letter", "%" + brand.getLetter() + "%");
            }
            if (brand.getId() != null) {
                criteria.andEqualTo(brand.getId());
            }
            if (brand.getSeq() != null) {
                criteria.andEqualTo(brand.getSeq());
            }
        }
        return example;
    }
}

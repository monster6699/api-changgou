package cn.monster.goods.controller;


import cn.monster.entity.Result;
import cn.monster.entity.StatusCode;
import cn.monster.goods.pojo.Brand;
import cn.monster.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<Brand> findAll() {
        List<Brand> all = brandService.findAll();
        return new Result<Brand>(true, StatusCode.OK, "查询成功", all);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id) {
        Brand byId = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "查询成功", byId);
    }

    @PostMapping("/add")
    public Result addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PostMapping("/update")
    public Result updateBrand(@RequestBody Brand brand) {
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @PostMapping("/delete")
    public Result deleteBrand(@RequestParam Integer id) {
        brandService.deleteBrand(id);
        return new Result(true, StatusCode.OK, "删除成功");

    }

    @GetMapping("/list")
    public Result<Brand> selectByBrand(@RequestParam(required = false) String name, @RequestParam(required = false) String image, @RequestParam(required = false) String letter, @RequestParam(required = false) Integer id, @RequestParam(required = false) Integer seq) {
        Brand brand = new Brand();
        brand.setId(id);
        if (seq != null) {
            brand.setSeq(seq);
        }
        if (!StringUtil.isEmpty(name)) {
            brand.setName(name);
        }
        if (!StringUtil.isEmpty(image)) {
            brand.setImage(image);
        }

        if (!StringUtil.isEmpty(letter)) {
            brand.setLetter(letter);
        }

        List<Brand> byBrandLIst = brandService.findByBrandLIst(brand);
        return new Result<Brand>(true, StatusCode.OK, "查询成功", byBrandLIst);
    }


    @GetMapping("/list/info")
    public Result<PageInfo> findBypage(@RequestParam Integer page, @RequestParam Integer size) {
        PageInfo<Brand> byPageList = brandService.findByPageList(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", byPageList);
    }


    @GetMapping("/list/info/page")
    public Result<PageInfo> selectByPageBrand(@RequestParam(required = false) String name, @RequestParam(required = false) String image, @RequestParam(required = false) String letter, @RequestParam(required = false) Integer id, @RequestParam(required = false) Integer seq, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "5") Integer size) {
        Brand brand = new Brand();
        brand.setId(id);

        brand.setSeq(seq);

        if (!StringUtil.isEmpty(name)) {
            brand.setName(name);
        }
        if (!StringUtil.isEmpty(image)) {
            brand.setImage(image);
        }

        if (!StringUtil.isEmpty(letter)) {
            brand.setLetter(letter);
        }

        PageInfo<Brand> byPageSelectList = brandService.findByPageSelectList(brand, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", byPageSelectList);
    }

}

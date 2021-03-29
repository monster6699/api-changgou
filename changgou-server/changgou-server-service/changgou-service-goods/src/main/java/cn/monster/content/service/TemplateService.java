package cn.monster.content.service;

import cn.monster.content.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Template业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface TemplateService {

    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(Template template, int page, int size);

    /***
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(int page, int size);

    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    List<Template> findList(Template template);

    /***
     * 删除Template
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Template数据
     * @param template
     */
    void update(Template template);

    /***
     * 新增Template
     * @param template
     */
    void add(Template template);

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
     Template findById(Integer id);

    /***
     * 查询所有Template
     * @return
     */
    List<Template> findAll();

    /***
     * 根据category查询template
     * @param categoryId
     * @return
     */
    Template findTemplateByCategory(Integer categoryId);
}
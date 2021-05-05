package com.jianyi.mall.Service;

import com.github.pagehelper.PageInfo;
import com.jianyi.mall.model.pojo.Category;
import com.jianyi.mall.model.request.AddCategoryReq;
import com.jianyi.mall.model.vo.CategoryVO;

import java.util.List;

/**
 * @Author: YANSHAO
 * @Description: CategoryService
 * @Date: 2021/2/6 13:44
 */

public interface CategoryService {

    /**
     *新增目录
     * @param addCategoryReq
     */
    void add(AddCategoryReq addCategoryReq);

    /**
     * 更新目录
     * @param updateCategory
     */
    void update(Category updateCategory);

    /**
     * 删除目录
     * @param id
     */
    void delete(Integer id);

    /**
     * 目录列表（管理员）
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 前台目录列表
     * @return
     */
    List<CategoryVO> listCategoryForCustomer();
}

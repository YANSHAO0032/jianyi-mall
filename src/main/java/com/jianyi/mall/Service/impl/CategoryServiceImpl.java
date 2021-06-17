package com.jianyi.mall.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jianyi.mall.Service.CategoryService;
import com.jianyi.mall.exception.JianyiMallException;
import com.jianyi.mall.exception.JianyiMallExceptionEnum;
import com.jianyi.mall.model.dao.CategoryMapper;
import com.jianyi.mall.model.pojo.Category;
import com.jianyi.mall.model.request.AddCategoryReq;
import com.jianyi.mall.model.vo.CategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YANSHAO
 * @Description: CategoryServiceImpl
 * @Date: 2021/2/6 13:44
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 新增目录
     * @param addCategoryReq
     */
    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq,category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null) {
            throw new JianyiMallException(JianyiMallExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.CREATE_FAILD);
        }
    }

    /**
     * 更新目录
     * @param updateCategory
     */
    @Override
    public void update(Category updateCategory) {
        if (updateCategory.getName() != null) {
            Category categoryOld = categoryMapper.selectByName(updateCategory.getName());
            if (categoryOld != null && !categoryOld.getId().equals(updateCategory.getId())) {
                throw new JianyiMallException(JianyiMallExceptionEnum.NAME_EXISTED);
            }
        }
        int count = categoryMapper.updateByPrimaryKeySelective(updateCategory);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.UPDATE_FAILED);
        }
    }

    /**
     * 删除目录
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        if (categoryOld == null) {
            throw new JianyiMallException(JianyiMallExceptionEnum.DELETE_FAILED);
        }
        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.DELETE_FAILED);
        }
    }

    /**
     * 目录列表（管理员）
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;
    }

    /**
     * 前台目录列表
     * @return
     */
    @Override
    @Cacheable(value = "listCategoryForCustomer")
    public List<CategoryVO> listCategoryForCustomer(Integer parentId) {
        ArrayList<CategoryVO> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList, parentId);
        return categoryVOList;
    }

    private void recursivelyFindCategories(List<CategoryVO> categoryVOList, Integer parentId) {
        //递归获取所有子类别，并组合成为一个“目录树”
        List<Category> categoryList = categoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category, categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildCategory(),categoryVO.getId());

            }
        }
    }

}

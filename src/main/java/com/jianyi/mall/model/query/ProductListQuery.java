package com.jianyi.mall.model.query;

import java.util.List;

/**
 * @Author: YANSHAO
 * @Description: ProductListQuery 查询商品列表的query
 * @Date: 2021/5/15 20:02
 */
public class ProductListQuery {

    private String keyword;

    private List<Integer> categoryIds;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}

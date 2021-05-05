package com.jianyi.mall.Service;

import com.jianyi.mall.model.pojo.Product;
import com.jianyi.mall.model.request.AddProductReq;

/**
 * @Author: YANSHAO
 * @Description: ProductService
 * 商品service
 * @Date: 2021/3/18 9:41
 */
public interface ProductService {

    /**
     * 后台新增商品
     * @param addProductReq
     *
     */
    void add(AddProductReq addProductReq);

    /**
     * 后台更新商品
     * @param updateProduct
     */
    void update(Product updateProduct);

    /**
     * 后台删除商品
     * @param id
     */
    void delete(Integer id);
}

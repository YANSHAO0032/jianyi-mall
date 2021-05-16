package com.jianyi.mall.Service;

import com.github.pagehelper.PageInfo;
import com.jianyi.mall.model.pojo.Product;
import com.jianyi.mall.model.request.AddProductReq;
import com.jianyi.mall.model.request.ProductListReq;

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

    /**
     * 后台批量上下架
     * @param ids
     * @param sellStatus
     */
    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);


    /**
     * 后台商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 商品详情
     * @param id
     * @return
     */
    Product detail(Integer id);

    /**
     * 商品列表
     * @param productListReq
     * @return
     */
    PageInfo list(ProductListReq productListReq);
}

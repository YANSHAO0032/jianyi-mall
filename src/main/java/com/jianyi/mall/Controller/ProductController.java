package com.jianyi.mall.Controller;

import com.github.pagehelper.PageInfo;
import com.jianyi.mall.Service.ProductService;
import com.jianyi.mall.common.ApiRestResponse;
import com.jianyi.mall.model.pojo.Product;
import com.jianyi.mall.model.request.ProductListReq;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YANSHAO
 * 前台商品Controller
 * @Description: ProductController
 * @Date: 2021/5/15 17:57
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation("商品详情")
    @GetMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @ApiOperation("商品详情")
    @GetMapping("product/list")
    public ApiRestResponse list(ProductListReq productListReq) {
        PageInfo list = productService.list(productListReq);
        return ApiRestResponse.success(list);
    }

}

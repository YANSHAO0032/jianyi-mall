package com.jianyi.mall.Service.impl;

import com.jianyi.mall.Service.ProductService;
import com.jianyi.mall.exception.JianyiMallException;
import com.jianyi.mall.exception.JianyiMallExceptionEnum;
import com.jianyi.mall.model.dao.ProductMapper;
import com.jianyi.mall.model.pojo.Product;
import com.jianyi.mall.model.request.AddProductReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YANSHAO
 * @Description: ProductServiceIml
 * 商品服务实现类
 * @Date: 2021/3/18 9:42
 */
@Service
public class ProductServiceIml implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(addProductReq.getName());
        if (productOld != null) {
            throw new JianyiMallException(JianyiMallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.CREATE_FAILD);
        }
    }


    @Override
    public void update(Product updateProduct) {
        Product productOld = productMapper.selectByName(updateProduct.getName());
        //同名且不同id，不能继续修改
        if (productOld != null && !productOld.getId().equals(updateProduct.getId())) {
            throw new JianyiMallException(JianyiMallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.updateByPrimaryKeySelective(updateProduct);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        Product productOld = productMapper.selectByPrimaryKey(id);
        //查不到该记录，无法删除
        if (productOld == null) {
            throw new JianyiMallException(JianyiMallExceptionEnum.DELETE_FALED);
        }
        int count = productMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.DELETE_FALED);
        }
    }
}

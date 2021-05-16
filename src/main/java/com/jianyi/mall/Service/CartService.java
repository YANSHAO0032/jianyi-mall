package com.jianyi.mall.Service;


import com.jianyi.mall.model.vo.CartVO;

import java.util.List;

/**
 * @Author: YANSHAO
 * @Description: CartService
 * @Date: 2021/2/3 11:29
 */
public interface CartService {

    /**
     * 购物车列表
     * @param userId
     * @return
     */
    List<CartVO> list(Integer userId);

    /**
     * 购物车添加
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    List<CartVO> add(Integer userId, Integer productId, Integer count);

    /**
     * 更新购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    List<CartVO> update(Integer userId, Integer productId, Integer count);

    /**
     * 删除购物车
     * @param userId
     * @param productId
     * @return
     */
    List<CartVO> delete(Integer userId, Integer productId);

    /**
     * 选择购物车
     * @param userId
     * @param productId
     * @param selected
     * @return
     */
    List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected);

    /**
     * 全选/全不选
     * @param userId
     * @param selected
     * @return
     */
    List<CartVO> selectAllOrNot(Integer userId, Integer selected);
}

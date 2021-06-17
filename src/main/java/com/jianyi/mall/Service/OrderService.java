package com.jianyi.mall.Service;

import com.github.pagehelper.PageInfo;
import com.jianyi.mall.model.request.CreateOrderReq;
import com.jianyi.mall.model.vo.OrderVO;

/**
 * @Author: YANSHAO
 * @Description: OrderService
 * @Date: 2021/5/22 21:25
 */
public interface OrderService {

    /**
     * 创建订单
     * @param createOrderReq
     * @return
     */
    String create(CreateOrderReq createOrderReq);

    /**
     * 前台订单详情
     * @param orderNo
     * @return
     */
    OrderVO detail(String orderNo);

    /**
     * 前台订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForCustomer(Integer pageNum, Integer pageSize);


    /**
     * 前台取消订单
     * @param orderNo
     */
    void cancel(String orderNo);


    /**
     * 生成支付二维码
     * @param orderNo
     * @return
     */
    String qrcode(String orderNo);

    /**
     * 管理员订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);


    /**
     * 支付接口
     * @param orderNo
     */
    void pay(String orderNo);

    /**
     * 发货
     * @param orderNo
     */
    void deliver(String orderNo);

    void finish(String orderNo);
}

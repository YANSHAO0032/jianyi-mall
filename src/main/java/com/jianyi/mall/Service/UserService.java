package com.jianyi.mall.Service;

import com.jianyi.mall.exception.JianyiMallException;
import com.jianyi.mall.model.pojo.User;


/**
 * @Author: YANSHAO
 * @Description: UserService
 * @Date: 2021/2/3 11:29
 */
public interface UserService {

    /**
     * 获取用户
     * @return
     */
    User getUser();

    /**
     * 注册
     * @param userName
     * @param password
     */
    void register(String userName, String password) throws JianyiMallException;


    /**
     * 登录
     * @param userName
     * @param password
     * @return
     * @throws JianyiMallException
     */
    User login(String userName, String password) throws JianyiMallException;


    /**
     * 更新个性签名
     * @param user
     * @throws JianyiMallException
     */
    void updateInformation(User user) throws JianyiMallException;


    /**
     * 验证是否管理员
     * @param user
     * @return
     */
    boolean checkAdminRole(User user);
}

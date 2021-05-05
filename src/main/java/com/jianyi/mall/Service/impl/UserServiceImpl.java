package com.jianyi.mall.Service.impl;

import com.jianyi.mall.Service.UserService;
import com.jianyi.mall.Util.MD5Utils;
import com.jianyi.mall.exception.JianyiMallException;
import com.jianyi.mall.exception.JianyiMallExceptionEnum;
import com.jianyi.mall.model.dao.UserMapper;
import com.jianyi.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @Author: YANSHAO
 * @Description: UserServiceImpl
 * @Date: 2021/2/3 11:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    /**
     * 注册
     *
     * @param userName
     * @param password
     */
    @Override
    public void register(String userName, String password) throws JianyiMallException {
        //查询用户名是否存在，不允许重名
        User result = userMapper.selectByName(userName);
        if (result != null){
            throw new JianyiMallException(JianyiMallExceptionEnum.NAME_EXISTED);
        }

        User user = new User();
        user.setUsername(userName);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new JianyiMallException(JianyiMallExceptionEnum.INSERT_FAILED);
        }
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     * @throws JianyiMallException
     */
    @Override
    public User login(String userName, String password) throws JianyiMallException{
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(userName, md5Password);
        if (user == null) {
            throw new JianyiMallException(JianyiMallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInformation(User user) throws JianyiMallException {
        //更新个性签名
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1) {
            throw new JianyiMallException(JianyiMallExceptionEnum.UPDATE_FAILED);
        }

    }

    @Override
    public boolean checkAdminRole(User user) {
        // 1:普通用户 2:管理员
        return user.getRole().equals(2);
    }
}

package com.jianyi.mall.Controller;

import com.jianyi.mall.Service.UserService;
import com.jianyi.mall.common.ApiRestResponse;
import com.jianyi.mall.common.Constant;
import com.jianyi.mall.exception.JianyiMallException;
import com.jianyi.mall.exception.JianyiMallExceptionEnum;
import com.jianyi.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author: YANSHAO
 * @Description: UserController
 * @Date: 2021/2/3 11:28
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User personalPage(){
        return userService.getUser();
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     * @throws JianyiMallException
     */
    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("userName") String userName, @RequestParam("password") String password) throws JianyiMallException {

        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(userName, password);
        return ApiRestResponse.success();

    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param session
     * @return
     * @throws JianyiMallException
     */
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) throws JianyiMallException {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //保存用户信息时，不保存代码
        user.setPassword(null);
        session.setAttribute(Constant.JIANYI_MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    /**
     * 更新个性签名
     * @param session
     * @param signature
     * @return
     * @throws JianyiMallException
     */
    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature) throws JianyiMallException {
        User currentUser = (User) session.getAttribute(Constant.JIANYI_MALL_USER);
        if (currentUser == null) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInformation(user);
        return ApiRestResponse.success();
    }

    /**
     * 登出，清除Session
     * @param session
     * @return
     */
    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session){
        session.removeAttribute(Constant.JIANYI_MALL_USER);
        return ApiRestResponse.success();
    }

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @param session
     * @return
     * @throws JianyiMallException
     */
    @PostMapping("/adminlogin")
    @ResponseBody
    public ApiRestResponse adminlogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) throws JianyiMallException {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //校验是否管理员
        if (userService.checkAdminRole(user)) {
            //保存用户信息时，不保存代码
            user.setPassword(null);
            session.setAttribute(Constant.JIANYI_MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(JianyiMallExceptionEnum.NEED_ADMIN);
        }

    }


}

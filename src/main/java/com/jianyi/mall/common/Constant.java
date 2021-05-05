package com.jianyi.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: YANSHAO
 * @Description: Constant
 * @Date: 2021/2/3 16:05
 */
@Component
public class Constant {
    /**
     * 盐值
     */
    public static final String SALT = "18dgj''l[l%$$DFG";

    public static final String JIANYI_MALL_USER = "jianyi_mall_user";


    public static String FILE_UPLOAD_DIR;
    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}

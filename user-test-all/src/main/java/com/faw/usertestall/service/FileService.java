package com.faw.usertestall.service;

import java.io.File;
import java.io.InputStream;

/**
 * 文件服务
 *
 * @author 鹿胜宝
 * @date 2023/03/29
 */
public interface FileService {

    void upload(InputStream inputStream, String fileName);

    void upload(File file);

}

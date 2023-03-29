package com.faw.usertestall.service.impl;

import com.faw.usertestall.domain.common.ErrorCode;
import com.faw.usertestall.exception.BusinessException;
import com.faw.usertestall.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * 本地文件服务实现类
 *
 * @author 鹿胜宝
 * @date 2023/03/29
 */
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {

    Logger logger = LoggerFactory.getLogger(LocalFileServiceImpl.class);

    private static final String BUCKET = "uploads";

    @Override
    public void upload(InputStream inputStream, String fileName) {
        //拼接文件上传的路径
        String path = BUCKET + "/" + fileName;

        //定义输入、输出流（TWR,try-with-resource）
        try (
                InputStream innerStream = inputStream;
                FileOutputStream fileOutputStream = new FileOutputStream(new File(path)); //不会自动创建文件夹，需要自己创建uploads文件夹

        ) {
            //定义缓冲区，每次从文件读取1024字节
            byte[] buffer = new byte[1024];
            int length;
            while ((length = innerStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            logger.error("文件上传失败！", e);
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILURE);
        }
    }

    @Override
    public void upload(File file) {
        try {
            upload(Files.newInputStream(file.toPath()), file.getName());
        } catch (Exception e) {
            logger.error("文件上传失败！", e);
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILURE);
        }
    }
}

package com.faw.usertestall.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.faw.usertestall.domain.common.PageQuery;
import com.faw.usertestall.domain.common.PageResult;
import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.domain.dto.UserExportDTO;
import com.faw.usertestall.domain.dto.UserQueryDTO;
import com.faw.usertestall.service.ExcelService;
import com.faw.usertestall.service.FileService;
import com.faw.usertestall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鹿胜宝
 * @date 2023/03/29
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private UserService userService;

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Override
    public void export(String filename, UserQueryDTO queryDTO) {

        //查询数据并导出到excel中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        dataToExcel(outputStream, queryDTO);

        //实现excel文件上传
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        fileService.upload(inputStream, filename);

    }

    @Async("exportServiceExecutor")
    @Override
    public void asyncExport(String filename, UserQueryDTO queryDTO) {
        export(filename, queryDTO);
    }

    private void dataToExcel(ByteArrayOutputStream outputStream, UserQueryDTO queryDTO) {
        //创建一个EasyExcel导出对象
        ExcelWriter excelWriter = EasyExcelFactory.write(outputStream, UserExportDTO.class).build();

        //分页查询
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<UserQueryDTO>();
        pageQuery.setQuery(queryDTO);
        pageQuery.setPageSize(2);

        int pageNo = 0;
        PageResult<List<UserDTO>> pageResult = new PageResult<List<UserDTO>>();

        do {
            pageQuery.setPageNo(++pageNo);
            if (logger.isInfoEnabled()) {
                logger.info("开始导出第【{}】页数据", pageNo);
            }
            pageResult = userService.query(pageQuery);

            List<UserExportDTO> exportDTOS = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExportDTO userExportDTO = new UserExportDTO();
                        BeanUtils.copyProperties(userDTO, userExportDTO);
                        return userExportDTO;
                    }).collect(Collectors.toList());
            //导出分页数据到excel中
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, "第" + pageNo + "页").build();
            excelWriter.write(exportDTOS, writeSheet);

            if (logger.isInfoEnabled()) {
                logger.info("结束导出第【{}】页数据！", pageNo);
            }
        } while (pageResult.getPageNum() > pageNo);

        //关闭excel文件流
        excelWriter.finish();
        if (logger.isInfoEnabled()) {
            logger.info("完成导出！");
        }
    }

}

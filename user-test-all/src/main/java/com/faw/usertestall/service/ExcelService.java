package com.faw.usertestall.service;

import com.faw.usertestall.domain.dto.UserQueryDTO;

/**
 * @author 鹿胜宝
 * @date 2023/03/29
 */
public interface ExcelService {
    void export(String filename, UserQueryDTO queryDTO);

    void asyncExport(String filename, UserQueryDTO queryDTO);
}

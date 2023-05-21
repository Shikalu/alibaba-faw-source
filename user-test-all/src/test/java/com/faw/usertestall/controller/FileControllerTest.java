package com.faw.usertestall.controller;

import com.faw.usertestall.domain.common.Result;
import com.faw.usertestall.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class FileControllerTest {

    @Mock(name = "localFileServiceImpl")
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    @Test
    public void testUpload() throws IOException {
        File file = new File("D:\\a.xlsx");
        MockMultipartFile testFile = null;
        testFile = new MockMultipartFile("file", "a.xlsx",
                MediaType.TEXT_PLAIN_VALUE, Files.newInputStream(file.toPath()));
        doNothing().when(fileService).upload(testFile.getInputStream(), testFile.getOriginalFilename());
        Result<String> upload = fileController.upload(testFile);
        assert ("a.xlsx".equals(upload.getData()));
    }
}
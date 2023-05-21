package com.faw.usertestall.controller;

import com.faw.usertestall.domain.common.PageQuery;
import com.faw.usertestall.domain.common.PageResult;
import com.faw.usertestall.domain.common.Result;
import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.domain.dto.UserQueryDTO;
import com.faw.usertestall.domain.vo.UserVO;
import com.faw.usertestall.service.ExcelService;
import com.faw.usertestall.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ExcelService excelService;


    @Test
    public void testSave() {
        //given
        UserDTO userDTO = new UserDTO();
        //when
        Mockito.when(userService.save(userDTO)).thenReturn(1);
        //then
        Result save = userController.save(userDTO);
        assert (Boolean.TRUE.equals(save.getSuccess()));

        //when
        Mockito.when(userService.save(userDTO)).thenReturn(2);
        //then
        Result save2 = userController.save(userDTO);
        assert ("B0001".equals(save2.getCode()));
    }

    @Test
    public void testUpdate() {
        //given
        UserDTO userDTO = new UserDTO();
        Long id = 3L;
        //when
        Mockito.when(userService.update(id, userDTO)).thenReturn(1);
        //then
        Result update = userController.update(id, userDTO);
        assert (Boolean.TRUE.equals(update.getSuccess()));

        //when
        Mockito.when(userService.update(id, userDTO)).thenReturn(2);
        //then
        Result update2 = userController.update(id, userDTO);
        assert ("B0001".equals(update2.getCode()));
    }

    @Test
    public void testDelete() {
        //given
        Long id = 3L;
        //when
        Mockito.when(userService.delete(id)).thenReturn(1);
        //then
        Result delete = userController.delete(id);
        assert (Boolean.TRUE.equals(delete.getSuccess()));

        //when
        Mockito.when(userService.delete(id)).thenReturn(2);
        //then
        Result delete2 = userController.delete(id);
        assert ("B0001".equals(delete2.getCode()));
    }

    @Test
    public void testGet() {
        //given
        Integer pageNo = 1;
        Integer pageSize = 20;
        UserQueryDTO queryDTO = new UserQueryDTO();

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("lilei");
        userDTO.setPassword("12345");
        userDTO.setEmail("7899@qq.com");
        userDTO.setAge(11);
        userDTO.setPhone("12433453212");
        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);
        PageResult<List<UserDTO>> listPageResult = new PageResult<>();
        listPageResult.setData(userDTOS);

        //when
        Mockito.when(userService.query(Mockito.any(PageQuery.class))).thenReturn(listPageResult);
        //then
        Result get = userController.get(pageNo, pageSize, queryDTO);
        List<UserVO> data = ((PageResult<List<UserVO>>) get.getData()).getData();
        assert (Objects.equals(data.get(0).getPassword(), "******")
                && Objects.equals(data.get(0).getUsername(), "lilei")
                && Objects.equals(data.get(0).getAge(), 11)
                && Objects.equals(data.get(0).getEmail(), "7899@qq.com")
                && Objects.equals(data.get(0).getPhone(), "12433453212"));
    }

    @Test
    public void testExport() {
        //given
        String fileName = "test";
        UserQueryDTO queryDTO = new UserQueryDTO();
        //when
        Mockito.doNothing().when(excelService).asyncExport(fileName, queryDTO);
        //then
        Result export = userController.export(fileName, queryDTO);
        assert (Boolean.TRUE.equals(export.getSuccess()));
    }
}
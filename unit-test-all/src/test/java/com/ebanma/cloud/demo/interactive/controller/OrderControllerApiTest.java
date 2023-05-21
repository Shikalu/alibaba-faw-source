package com.ebanma.cloud.demo.interactive.controller;

import com.ebanma.cloud.demo.interactive.validator.OrderRequestValidator;
import com.ebanma.cloud.demo.service.OrderApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author 鹿胜宝
 * @date 2023/05/19
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderApplicationService orderApplicationService;

    @MockBean
    private OrderRequestValidator orderRequestValidator;

    @Test
    public void should_invoke_order_service_to_create_order_given_validation_pass() {

    }
}

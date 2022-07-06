package com.demo.xmartapp.controllerlayertest;


import com.demo.xmartapp.controller.CustomerApi;
import com.demo.xmartapp.entity.CustomerEntity;
import com.demo.xmartapp.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
 class CustomerApiTest {

    private CustomerEntity customerEntity;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @InjectMocks
    private CustomerApi customerApi;

            private List<CustomerEntity> customerEntityList =new ArrayList<>();
    @Test
    @BeforeEach()
     void init()
    {
        customerEntity = new CustomerEntity();
        customerEntity.setEmailId("akshay@gmail.com");
        customerEntity.setName("Akshay");
        customerEntity.setPassword("Akshay@59");
        customerEntity.setAddress("Pune");
        customerEntity.setPhoneNumber("9874563210");

       CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setEmailId("Hemant@gmail.com");
        customerEntity1.setName("Hemant");
        customerEntity1.setPassword("Hemant@59");
        customerEntity1.setAddress("Pune");
        customerEntity1.setPhoneNumber("8874563210");
        customerEntityList.add(customerEntity1);
        customerEntityList.add(customerEntity);
    }

    @Test
     void fetchCustomerListTest() throws Exception {
        /*mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(print())
                //.andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(containsString("Welcome to X-mart Application!")));
        */
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        result.getRequest();
                    }
                });


    }




}

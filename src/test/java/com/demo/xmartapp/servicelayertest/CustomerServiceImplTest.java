package com.demo.xmartapp.servicelayertest;

import com.demo.xmartapp.dto.CustomerDTO;
import com.demo.xmartapp.entity.CustomerEntity;
import com.demo.xmartapp.repository.CustomerRepository;
import com.demo.xmartapp.service.CustomerService;
import com.demo.xmartapp.service.CustomerServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class CustomerServiceImplTest {

    private CustomerEntity customerEntity;
    private CustomerDTO customerDTO;
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerServiceImpl = new CustomerServiceImpl();
    @Test
    @BeforeEach()
     void init()
    {
        customerEntity = new CustomerEntity();
        customerEntity.setEmailId("akshada@gmail.com");
        customerEntity.setName("Akshada");
        customerEntity.setPassword("Akshada@59");
        customerEntity.setAddress("Pune");
        customerEntity.setPhoneNumber("9874563220");
        customerDTO = new CustomerDTO();
        customerDTO.setName(customerEntity.getName());
        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setPassword(customerEntity.getPassword());
        customerDTO.setEmailId(customerEntity.getEmailId());
        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());

    }

    @Test
    @Order(3)
   void deleteCustomerByIdTest()
    {
        doNothing().when(customerRepository).deleteById(customerEntity.getEmailId());
        customerServiceImpl.deleteCustomerById(customerEntity.getEmailId());
    }
    @Test
    @Rollback(value = false)
    @Order(1)
     void registerNewCustomerTest()
    {


        when(customerRepository.save(Mockito.any(CustomerEntity.class))).thenReturn(customerEntity);
        Assertions.assertEquals(customerEntity.getEmailId(), customerServiceImpl.registerNewCustomer(customerDTO).getEmailId());

    }
    @Test
    @Rollback(value = false)
    @Order(2)
     void fetchCustomerListTest()
    {
        List<CustomerEntity> customerEntityList =new ArrayList<>();
        CustomerEntity customerEntity1 =new CustomerEntity();
        customerEntity1.setEmailId("hemant@gmail.com");
        customerEntity1.setName("Hemant");
        customerEntity1.setPassword("Hemant@59");
        customerEntity1.setAddress("Pune");
        customerEntity1.setPhoneNumber("8874563220");
        customerEntityList.add(customerEntity1);
        customerEntityList.add(customerEntity);

        when(customerRepository.findAll()).thenReturn(customerEntityList);
        Assertions.assertEquals(2,customerServiceImpl.fetchCustomerList().size());
    }

    @Test
    void updatePasswordTest()
    {

        when(customerRepository.findById(customerEntity.getEmailId())).thenReturn(java.util.Optional.of(customerEntity));
        customerServiceImpl.updatePassword(customerDTO);
        verify(customerRepository).save(customerEntity);
    }
    /*@Test
    void findByIdNotNullTest()
    {
        when(customerRepository.findById(customerEntity.getEmailId())).thenReturn(java.util.Optional.of(customerEntity));
                Assertions.assertNotNull(customerRepository.findById(customerEntity.getEmailId()));
    }*/


    @Test
    void updatePasswordNullTest()
    {
        customerDTO.setEmailId(null);
         when(customerRepository.findById(customerEntity.getEmailId())).thenReturn(java.util.Optional.of(customerEntity));
        Assertions.assertNull(customerServiceImpl.updatePassword(customerDTO));
    }
    @Test
    void updatePasswordEmptyTest()
    {
        customerDTO.setEmailId("");
        when(customerRepository.findById(customerEntity.getEmailId())).thenReturn(java.util.Optional.of(customerEntity));
        Assertions.assertNull(customerServiceImpl.updatePassword(customerDTO));
    }
}

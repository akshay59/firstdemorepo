package com.demo.xmartapp.service;

import com.demo.xmartapp.dto.CustomerDTO;
import com.demo.xmartapp.entity.CustomerEntity;
import com.demo.xmartapp.repository.CustomerRepository;


import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepositoryImpl;

    

    @Override
    public CustomerEntity registerNewCustomer(CustomerDTO customerDTO)
	{

		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setEmailId(customerDTO.getEmailId());
		customerEntity.setAddress(customerDTO.getAddress());
		customerEntity.setPassword(customerDTO.getPassword());
		customerEntity.setName(customerDTO.getName());
		customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());

		return customerRepositoryImpl.save(customerEntity);
    }
    
    
    @Override
	public List<CustomerEntity> fetchCustomerList()
    {
        return customerRepositoryImpl.findAll();
    }

	@Override
	public CustomerEntity updatePassword(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setEmailId(customerDTO.getEmailId());
		customerEntity.setAddress(customerDTO.getAddress());
		customerEntity.setPassword(customerDTO.getPassword());
		customerEntity.setName(customerDTO.getName());
		customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
		String id= customerDTO.getEmailId();

		if(id !=null &&  !("".equals(id)))
		{

		 CustomerEntity customerEntityBody = customerRepositoryImpl.findById(id)
				 								.get();


		 if (Objects.nonNull(customerEntity.getPassword()) && !"".equalsIgnoreCase(customerEntity.getPassword()))
	    	 customerEntityBody.setPassword(customerEntity.getPassword());

		 return customerRepositoryImpl.save(customerEntityBody);
		}

				return null;
     }

	
	@Override
	public void deleteCustomerById(String emailId) {
		customerRepositoryImpl.deleteById(emailId);		
	}
    
    
}

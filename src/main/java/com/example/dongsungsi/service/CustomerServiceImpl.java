package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.CustomerDao;
import com.example.dongsungsi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : jc
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         jc          최초 생성
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Optional<Customer> findById(long id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        long seqId=0;
        if (customer.getId() == null) {
            customerDao.insertCustomer(customer);

        }else{
            customerDao.updateCustomer(customer);
        }
        seqId = customer.getId();
        return customerDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long id) {
        long queryResult =0;

        queryResult = customerDao.deleteCustomer(id);


        return (queryResult >=1) ? true:false;
    }

    @Override
    public boolean deleteAll() {
        long queryResult=0;
        queryResult = customerDao.deleteAll();
        return (queryResult >=1)?true:false;
    }
}

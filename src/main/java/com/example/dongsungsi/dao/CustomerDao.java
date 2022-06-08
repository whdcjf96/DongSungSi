package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.dao
 * fileName : CustoemrDao
 * author : jc
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         jc          최초 생성
 */
@Mapper
public interface CustomerDao {
    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    long insertCustomer(Customer customer);

    long updateCustomer(Customer customer);

    long deleteCustomer(Long id);

    long deleteAll();
}

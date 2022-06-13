package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;
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
    //    Todo : 1개 수정 findAll, 2개 추가 findByEmailContaining, selectTotalCount
//    Todo : findAll() -> findAll(Criteria criteria)
    List<Customer> findAll(Criteria criteria);

    //    Email로 검색 메소드
    List<Customer> findByEmailContaining(Criteria criteria);

    //    Email로 검색시 총 건수 메소드
    int selectTotalCount(String email);


    Optional<Customer> findById(Long id);

    long insertCustomer(Customer customer);

    long updateCustomer(Customer customer);

    long deleteCustomer(Long id);

    long deleteAll();
}

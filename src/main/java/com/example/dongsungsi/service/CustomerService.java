package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerService
 * author : jc
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         jc          최초 생성
 */
public interface CustomerService {
//    Todo : 1개 수정 findAll, 1개 추가 findByEmailContaining

//    id로 조회하는 메소드
    Optional<Customer> findById(long id);

//    전체 조회하는 메소드
    List<Customer> findAll(Criteria criteria);

    List<Customer> findByEmailContaining(Criteria criteria);

//    데이터 삽입/수정하는 메소드
    Optional<Customer> save(Customer customer);

//    데이터 삭제 메소드
    boolean deleteById(Long id);

//    데이터 전체 삭제 메소드
    boolean deleteAll();
}

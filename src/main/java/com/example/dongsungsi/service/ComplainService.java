package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Complain;
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
public interface ComplainService {

//    id로 조회하는 메소드
    Optional<Complain> findByNo(long no);

//    전체 조회하는 메소드
    List<Complain> findAll(Criteria criteria);

    List<Complain> findByTitleContaining(Criteria criteria);

//    데이터 삽입/수정하는 메소드
    Optional<Complain> save(Complain complain);

//    데이터 삭제 메소드
    boolean deleteByNo(Long no);

//    데이터 전체 삭제 메소드
    boolean deleteAll();
}

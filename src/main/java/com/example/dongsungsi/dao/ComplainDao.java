package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.Complain;
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
public interface ComplainDao {
    List<Complain> findAll(Criteria criteria);

    //    title로 검색 메소드
    List<Complain> findByTitleContaining(Criteria criteria);

    //    title로 검색시 총 건수 메소드
    int selectTotalCount(String title);


    Optional<Complain> findByNo(Long no);

    long insertComplain(Complain complain);

    long updateComplain(Complain complain);

    long deleteComplain(Long no);

    long deleteAll();
}

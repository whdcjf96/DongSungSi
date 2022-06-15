package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.ComplainDao;
import com.example.dongsungsi.dao.CustomerDao;
import com.example.dongsungsi.model.Complain;
import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class ComplainServiceImpl implements ComplainService{
    @Autowired
    private ComplainDao complainDao;

    @Override
    public Optional<Complain> findByNo(long no) {
        return complainDao.findByNo(no);
    }
// Todo : 1개 수정 findAll, 1개 추가 findByEmailContaining
    @Override
    public List<Complain> findAll(Criteria criteria) {
        return complainDao.findAll(criteria);
    }

    @Override
    public List<Complain> findByTitleContaining(Criteria criteria) {
        List<Complain> complains = Collections.emptyList();

//        null 체크
        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());

//        테이블의 총 건수를 조회하는 메소드 호출
        int totalCount = complainDao.selectTotalCount(optionalCriteria.orElse(""));

//        Criteria 객체의 변수에 저장 : 페이지정보(totalCount, totalPages)
        criteria.setTotalItems(totalCount); // 테이블 총 건수 저장
        criteria.setTotalPages(totalCount/criteria.getSize());

        if(criteria.getTitle()==null){
            complains= complainDao.findAll(criteria);
        }else{
            complains= complainDao.findByTitleContaining(criteria);
        }

        return complains;
    }

    @Override
    public Optional<Complain> save(Complain complain) {
        long seqNo=0;
        if (complain.getNo() == null) {
            complainDao.insertComplain(complain);

        }else{
            complainDao.updateComplain(complain);
        }
        seqNo = complain.getNo();
        return complainDao.findByNo(seqNo);
    }

    @Override
    public boolean deleteByNo(Long no) {
        long queryResult =0;

        queryResult = complainDao.deleteComplain(no);


        return (queryResult >=1) ? true:false;
    }

    @Override
    public boolean deleteAll() {
        long queryResult=0;
        queryResult = complainDao.deleteAll();
        return (queryResult >=1)?true:false;
    }
}

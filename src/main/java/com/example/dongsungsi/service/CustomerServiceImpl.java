package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.CustomerDao;
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
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Optional<Customer> findById(long id) {
        return customerDao.findById(id);
    }
// Todo : 1개 수정 findAll, 1개 추가 findByEmailContaining
    @Override
    public List<Customer> findAll(Criteria criteria) {
        return customerDao.findAll(criteria);
    }

    @Override
    public List<Customer> findByEmailContaining(Criteria criteria) {
        List<Customer> customers = Collections.emptyList();

//        null 체크
        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getEmail());

//        테이블의 총 건수를 조회하는 메소드 호출
        int totalCount = customerDao.selectTotalCount(optionalCriteria.orElse(""));

//        Criteria 객체의 변수에 저장 : 페이지정보(totalCount, totalPages)
        criteria.setTotalItems(totalCount); // 테이블 총 건수 저장
        criteria.setTotalPages(totalCount/criteria.getSize());

        if(criteria.getEmail()==null){
//            email 없으면 전체 검색
            customers= customerDao.findAll(criteria);
        }else{
//            email 있으면 email 검색
            customers= customerDao.findByEmailContaining(criteria);
        }

        return customers;
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

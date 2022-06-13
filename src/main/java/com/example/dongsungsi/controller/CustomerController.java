package com.example.dongsungsi.controller;

import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;
import com.example.dongsungsi.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustoemrController
 * author : jc
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         jc          최초 생성
 */
@CrossOrigin(origins = "http://localhost:8081")
// @RestController : 통신을 json 형태로 주고받고 싶을 때 사용
@RestController
// @RequestMapping("/api") : http://localhost:8000/api
@RequestMapping("/api")
public class CustomerController {
    // logger 찍기를 위한 객체 만듬
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Autowired : 스프링 객체 하나 받아오기
    @Autowired
    private CustomerServiceImpl customerService; // 객체 정의( null => 스프링 객체)

    // 모든 회원 조회 메뉴
    @GetMapping("/customers")
    public ResponseEntity<Map<String,Object>> getAllCustomersPage(Criteria criteria) {

        logger.info("criteria(vue에서 전송됨) {}",criteria);

        // 모든 회원 조회 서비스 호출
        List<Customer> customers = customerService.findByEmailContaining(criteria);
        logger.info("customers {}", customers);

        try {
            if(customers.isEmpty()){
//                Vue로 내용물 없다고 전송(HttpStatus.NO_CONTENT)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("실행 후 : criteria {}", criteria);

//            Todo: Map에 넣어 객체와 페이지정보를 Vue로 보냄
            Map<String, Object> response = new HashMap<>();
            response.put("customers",customers);
            response.put("currentPage",criteria.getPage());
            response.put("totalItems",criteria.getTotalItems());
            response.put("totalPages",criteria.getTotalPages());
            // Vue 성공메세지 + 객체를 전송 + 페이지정보 전송
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러메세지 전송
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원 생성 메뉴
    @PostMapping("/customers")
    public ResponseEntity<Object>
    createCustomer(@RequestBody Customer customer) {
        // save 리턴값 Optional<Customer> => save(customer).get() 객체를 꺼냄
        logger.info("customer {}", customer);

        try {
            Customer savedCustomer = customerService.save(customer).get();
            return new ResponseEntity<Object>(savedCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id 로 회원 조회 메뉴
    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id) {
        // id 로 회원 조회하는 서비스를 호출 ( Optional 떼내기 : get() )
        Customer customer = customerService.findById(id).get();

        try {
            if(customer != null) {
                // 성공 시 Vue 에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(customer,HttpStatus.OK);
            }
            else {
                // Vue 에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

    }

//    id 회원 수정 메뉴
//    Vue에서 전송  : url 매개변수 + 객체
    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){

        try{
            Customer savedCustomer = customerService.save(customer).get();
            return new ResponseEntity<Object>(savedCustomer, HttpStatus.OK);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

//    id로 회원 삭제 메뉴
    @PutMapping("/customers/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id){

        try{
            customerService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}
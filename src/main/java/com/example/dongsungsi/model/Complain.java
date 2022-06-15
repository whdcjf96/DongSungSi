package com.example.dongsungsi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.customerspring.model
 * fileName : Customer
 * author : jc
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         jc          최초 생성
 */
@Getter
@Setter
@ToString
public class Complain {
//    Long(객체), long(일반 자료형)
    private Long no;
    private String title;
    private String content;
    private String targetPage;
    private String writer;
    private String deleteYn;
    private String insertTime;
    private String updateTime;
    private String deleteTime;
}

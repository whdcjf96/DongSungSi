package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Tutorial;
import com.example.dongsungsi.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.dongsungsi.service
 * fileName : TutorialService
 * author : jc
 * date : 2022-05-25
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         jc          최초 생성
 */
public interface TutorialService {
    Optional<Tutorial> findById(Long id);
    List<Tutorial> findByPublished(String published);
    List<Tutorial> findByTitleContaining(Criteria criteria);
    List<Tutorial> findAll(Criteria criteria);

//    insert / update 같이 구성 메소드
    public boolean save(Tutorial tutorial);

    public boolean deleteById(Long id);
    public boolean deleteAll();
}

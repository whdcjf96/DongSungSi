package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.Tutorial;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.dongsungsi.dao
 * fileName : TutorialDao
 * author : jc
 * date : 2022-05-25
 * description : DB CRUD를 위한 인터페이스 (xml 마이바티스 연동)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         jc          최초 생성
 */
@Mapper
public interface TutorialDao {
//     --------------- SELECT----------------
//    화면에서 publish 버튼을 클릭할 때 적용되는 메소드
    List<Tutorial> findByPublished(String published);

//    제목 검색을 위한 메소드
    List<Tutorial> findByTitleContaining(String title);

//    모든 데이터 목록 조회
    List<Tutorial> findAll();

//    id에 해당하는 값을 조회
//    Optional : null 을 방지하는 클래스
    Optional<Tutorial> findById(Long id);
//      ---------------------------------------
//      ---------------------------------------

//    Tutorial 테이블에 데이터 넣기 메소드(insert)
    int insertTutorial(Tutorial tutorial);

//    Tutorial 테이블에 데이터 수정 메소드(update)
    int updateTutorial(Tutorial tutorial);

//    Tutorial 테이블에 데이터 삭제 메소드(deleteYn = 'Y')
    int deleteTutorial(Long id);

//    Tutorial 테이블에 데이터 모두 삭제 메소드(deleteYn = 'Y')
    int deleteAll();






}

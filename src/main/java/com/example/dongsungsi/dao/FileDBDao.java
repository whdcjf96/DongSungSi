package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.FileDB;
import com.example.dongsungsi.model.FileDB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName : com.example.dongsunguploaddb.dao
 * fileName : FileDBDao
 * author : jc
 * date : 2022-05-31
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31         jc          최초 생성
 */
@Mapper
public interface FileDBDao {
//    id로 이미지를 찾는 메소드 (한건)
    FileDB findById(String id);

//    모든 이미지 목록을 찾는 메소드(여러건)
    List<FileDB> findAll();

//    업로드 이미지 저장 메소드
    int save(FileDB fileDB);

}

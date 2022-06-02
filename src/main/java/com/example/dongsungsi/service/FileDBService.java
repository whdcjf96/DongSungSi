package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.FileDBDao;
import com.example.dongsungsi.model.FileDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * packageName : com.example.dongsunguploaddb.service
 * fileName : FileStorageService
 * author : jc
 * date : 2022-05-31
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31         jc          최초 생성
 */
@Service
public class FileDBService {

    @Autowired
    private FileDBDao fileDBDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    insert 문 호출하는 서비스(이미지 DB 저장됨)
    public int store(MultipartFile file) throws IOException {
//        매개변수로 들어오는 이미지파일의 경로를 제거해서 순수 파일이름만 얻기
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

//        이미지 파일이 생성됨
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

//        DB에 insert문 호출
        return fileDBDao.save(fileDB);
    }

    public FileDB getFile(String id){
        return fileDBDao.findById(id);
    }

    public Stream<FileDB> getAllFile(){
//        List, Map, Set => 인터페이스
//        ArrayList, HashMap, HashSet
//        List, Map, Set => Stream 변환 가능
//        Stream => List, Map, Set 변환 가능
//        Stream 편한 함수들을 쓰기 위해(map, foreach, filter)
        Stream<FileDB> resFileDB = fileDBDao.findAll().stream();

        return resFileDB;
    }

}

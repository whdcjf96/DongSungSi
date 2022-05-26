package com.example.dongsungsi.controller;

import com.example.dongsungsi.model.Tutorial;
import com.example.dongsungsi.service.TutorialServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * packageName : com.example.dongsungsi.controller
 * fileName : TutorialController
 * author : jc
 * date : 2022-05-25
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         jc          최초 생성
 */
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialServiceImpl tutorialService;

//  insert
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        boolean bSuccess =tutorialService.save(tutorial);

        try{
            if(bSuccess==true){
//                ResponseEntity<>(매개변수객체,상태정보)
                return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    id select
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id){
        Optional<Tutorial> tutorial=tutorialService.findbyId(id);
//        isPresent() : Optional 메소드, 값이 있으면
        if(tutorial.isPresent()){
//            ResponseEntity<>(객체, 상태정보) => 프론트엔드 전송
            return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    title select
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getTitleTutorials(@RequestParam(required = false) String title){
//        title(제목)을 조회하는 서비스를 호출
        List<Tutorial> tutorials = tutorialService.findByTitleContaining(title);
        try{
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    update : @PutMapping
//    @PathVariable : url의 {}부분
//    @RequestBody : json 객체형태로 데이터 받기
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorial){
//        id에 해당하는 데이터가 있는지 조회
//        Optional<객체> : 객체가 null이면 ""자동으로 바꿈(null포인트에러 방지)
        Optional<Tutorial> tutorialData= tutorialService.findbyId(id);

        if(tutorialData.isPresent()){
//            tutorialData 가 있으면 save호출( id가 있으면 update)
            boolean bSuccess = tutorialService.save(tutorial);

            if (bSuccess == true) {
//                save 호출 성공하면
                return new ResponseEntity<>(tutorial,HttpStatus.CREATED);
            }
//            save 호출 실패하면
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
//            tutorialData가 없으면
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    delete
    @PutMapping("/tutorials/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id){
        boolean bSuccess = tutorialService.deleteById(id);

//        프론트엔드 쪽으로 상태정보를 보내줌
        try{
            if (bSuccess == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
//            update문이 실패했을 경우(0건 수정이 될 경우)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    delete all
    @PutMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials(){
        boolean Bsuccess = tutorialService.deleteAll();

        try {
            if (Bsuccess == true){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findbyPublished(){
        List<Tutorial> tutorials = tutorialService.findByPublished("Y");

        try{
            if (tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

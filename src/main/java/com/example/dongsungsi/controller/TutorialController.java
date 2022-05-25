package com.example.dongsungsi.controller;

import com.example.dongsungsi.model.Tutorial;
import com.example.dongsungsi.service.TutorialServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.TutorialDao;
import com.example.dongsungsi.model.Tutorial;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * packageName : com.example.dongsungsi.service
 * fileName : TutorialServiceImpl
 * author : jc
 * date : 2022-05-25
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         jc          최초 생성
 */
@Service
public class TutorialServiceImpl implements TutorialService{
    @Autowired
    TutorialDao tutorialDao;

//    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Optional<Tutorial> findbyId(Long id) {
        return tutorialDao.findById(id);
    }

    @Override
    public List<Tutorial> findByPublished(String published) {
        return tutorialDao.findByPublished(published);
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        List<Tutorial> tutorials = Collections.emptyList();
        if (title == null) {
            tutorials=tutorialDao.findAll();
        }else{
            tutorials=tutorialDao.findByTitleContaining(title);
        }
        return tutorials;
    }

    @Override
    public List<Tutorial> findAll() {
        List<Tutorial> tutorials = Collections.emptyList();
        tutorials=tutorialDao.findAll();
        return tutorials;
    }

    @Override
    public boolean save(Tutorial tutorial) {
        int queryResult=0;
        if (tutorial.getId()==null){
            queryResult=tutorialDao.insertTutorial(tutorial);
        }else{
            queryResult=tutorialDao.updateTutorial(tutorial);
        }
        return (queryResult >=1 )?true:false;
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult=0;

        queryResult=tutorialDao.deleteTutorial(id);
        return (queryResult >=1 )?true:false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult=0;

        queryResult=tutorialDao.deleteAll();
        return (queryResult >=1 )?true:false;
    }
}

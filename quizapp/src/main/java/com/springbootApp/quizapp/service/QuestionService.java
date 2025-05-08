package com.springbootApp.quizapp.service;

import com.springbootApp.quizapp.dao.QuestionDao;
import com.springbootApp.quizapp.model.QuizQuestion;
import com.springbootApp.quizapp.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<QuizQuestion>>  getAllQuestions(){
        try{
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public List<QuizQuestion>  getQuestionByCategory(String category){
        return questionDao.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(QuizQuestion question) {
         questionDao.save(question);
         return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }
}

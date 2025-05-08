package com.springbootApp.quizapp.controller;

import com.springbootApp.quizapp.model.QuizQuestion;
import com.springbootApp.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class questionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<QuizQuestion>> getAllQuestions(){

        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<QuizQuestion>> getQuestionByCategory(@PathVariable String category){

        return new ResponseEntity<>(questionService.getQuestionByCategory(category), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody QuizQuestion question){

        return questionService.addQuestion(question);
    }
}

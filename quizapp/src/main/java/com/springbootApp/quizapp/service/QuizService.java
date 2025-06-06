package com.springbootApp.quizapp.service;

import com.springbootApp.quizapp.dao.QuestionDao;
import com.springbootApp.quizapp.dao.QuizDAO;
import com.springbootApp.quizapp.model.QuestionWrapper;
import com.springbootApp.quizapp.model.Quiz;
import com.springbootApp.quizapp.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<QuizQuestion> quizQuestions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(quizQuestions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<QuizQuestion> questionsFromdb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(QuizQuestion q: questionsFromdb ) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }
}

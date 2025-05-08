package com.springbootApp.quizapp.dao;

import com.springbootApp.quizapp.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuizQuestion, Integer> {
    //List<QuizQuestion> findAll();
    List<QuizQuestion> findByCategory(String category);
    @Query(value="SELECT * from quiz_question q where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<QuizQuestion> findRandomQuestionsByCategory(String category, int numQ);

}

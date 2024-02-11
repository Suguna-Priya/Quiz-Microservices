package com.example.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.quizservice.dao.QuizDao;
import com.example.quizservice.dto.QuizDto;
import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.Questions;
import com.example.quizservice.entity.Quiz;
import com.example.quizservice.entity.Reponse;
import com.example.quizservice.feign.FeignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;

	@Autowired
	FeignInterface feignInterface;

	public ResponseEntity<String> createQuizService(QuizDto quizDto) {

		List<Integer> questionIds = feignInterface.getQuestionsForQuiz(quizDto.getCategory(),quizDto.getNumQ()).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(quizDto.getTitle());
		quiz.setQuestionIds(questionIds);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

		Quiz quiz = quizDao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();

        return feignInterface.getQuestionById(questionIds);
	}


	public ResponseEntity<Integer> calculateSubmitedResponse(List<Reponse> responses) {

        return feignInterface.getScore(responses);
	}
	

}

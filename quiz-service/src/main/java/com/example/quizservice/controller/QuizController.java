package com.example.quizservice.controller;

import java.util.List;

import com.example.quizservice.dto.QuizDto;
import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.Reponse;
import com.example.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){

		return quizService.createQuizService(quizDto);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
		
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submit")
	public ResponseEntity<Integer> SubmitResponse(@RequestBody List<Reponse> responses){
		return quizService.calculateSubmitedResponse(responses);
	}

}

package com.example.questionservice.controller;

import java.util.List;

import com.example.questionservice.entity.QuestionWrapper;
import com.example.questionservice.entity.Questions;
import com.example.questionservice.entity.Reponse;
import com.example.questionservice.service.questionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private questionSerivce quesService;
	
	@GetMapping("allQuestions")
	public List<Questions> getAllQuestions() {
		return quesService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public List<Questions> getQuestionsByCategory(@PathVariable("category") String category){
		return quesService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public String addQuestion(@RequestBody Questions question) {

		return quesService.addQuestion(question);
	}

	@PostMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numQ){
		return quesService.getQuestionForQuiz(category,numQ);
	}

	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionById(@RequestBody List<Integer> QuestionIds){
		return quesService.getQuestionsById(QuestionIds);
	}

	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Reponse> response){
		return quesService.calculateScore(response);
	}
}

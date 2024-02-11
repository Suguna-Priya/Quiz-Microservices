package com.example.questionservice.service;

import java.util.ArrayList;
import java.util.List;


import com.example.questionservice.dao.QuestionDao;
import com.example.questionservice.entity.QuestionWrapper;
import com.example.questionservice.entity.Questions;
import com.example.questionservice.entity.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class questionSerivce {
	
	@Autowired
	private QuestionDao questiondao;
	
	public List<Questions> getAllQuestions(){
		return questiondao.findAll(); 
	}

	public List<Questions> getQuestionsByCategory(String category) {
		
		return questiondao.findByCategory(category);
	}

	public String addQuestion(Questions question) {
		questiondao.save(question);
		return "Success";
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQ) {
		List<Integer> QuestionNums = questiondao.getRandomQuestionsByCategory(category,numQ);
		return new ResponseEntity<>(QuestionNums, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds) {
		List<Questions> questionsList = new ArrayList<>();
		List<QuestionWrapper> questionWrapperList = new ArrayList<>();

		for(Integer i:questionIds){
			questionsList.add(questiondao.findById(i).get());
		}

		for(Questions q:questionsList){
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(q.getId());
			wrapper.setQuestionTitle(q.getQuestionTitle());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());

			questionWrapperList.add(wrapper);
		}

		return new ResponseEntity<>(questionWrapperList,HttpStatus.OK) ;
	}

	public ResponseEntity<Integer> calculateScore(List<Reponse> response) {

		int right = 0;

		for(Reponse res : response) {
			Questions questions = questiondao.findById(res.getId()).get();
			if(res.getResponse().equals(questions.getRightAnswer()))
				right++;
		}

		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}

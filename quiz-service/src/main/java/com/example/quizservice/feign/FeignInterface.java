package com.example.quizservice.feign;

import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.Reponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface FeignInterface {

    @PostMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);


    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionById(@RequestBody List<Integer> QuestionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Reponse> response);
}

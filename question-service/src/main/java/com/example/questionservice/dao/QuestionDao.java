package com.example.questionservice.dao;

import java.util.List;

import com.example.questionservice.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer> {

	List<Questions> findByCategory(String category);

	@Query(value="SELECT q.id FROM questions q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery=true)
	List<Integer> getRandomQuestionsByCategory(String category, int numQ);
}

package com.example.quizservice.dto;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private int numQ;
    private String title;
}

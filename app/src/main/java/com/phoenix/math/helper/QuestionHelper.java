package com.phoenix.math.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dahlia on 2/25/17.
 */
public class QuestionHelper {
    public static List<Question> getQuestions(){
        List<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion2s1(10, 3);
            questions.add(question);
        }
        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion2s1(50, 3);
            questions.add(question);
        }
        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion2s2(15, 3);
            questions.add(question);
        }


        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s1(10, 3);
            questions.add(question);
        }


        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s1(20, 3);
            questions.add(question);
        }


        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s1(50, 3);
            questions.add(question);
        }



        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s2(10, 10, 3);
            questions.add(question);
        }


        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s2(20, 15, 3);
            questions.add(question);
        }


        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s2(50, 10, 3);
            questions.add(question);
        }



        for (int i = 0; i < 5; i++) {
            Question question = MathQuestion.simpleQuestion3s3(10, 2);
            questions.add(question);
        }
        return questions;
    }
}

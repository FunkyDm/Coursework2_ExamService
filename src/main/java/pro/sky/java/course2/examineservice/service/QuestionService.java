package pro.sky.java.course2.examineservice.service;

import pro.sky.java.course2.examineservice.domain.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(String question, String answer);

    Collection<Question> getALl();

    Question getRandomQuestion();
}

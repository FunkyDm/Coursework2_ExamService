package pro.sky.java.course2.examineservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exceptions.RequestLimitExceededException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random ran = new Random();

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getALl().size()) {
            throw new RequestLimitExceededException("Запрошено большее кол-во вопросов, чем хранится в сервисе");
        }
        int i = 0;
        while (i <= amount) {
            questionService.getRandomQuestion();
            i++;
        }
        return Collections.unmodifiableCollection(questionService.getALl());
    }

}

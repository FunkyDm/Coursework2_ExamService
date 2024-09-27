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
        if (amount > questionService.getAll().size()) {
            throw new RequestLimitExceededException("Запрошено большее кол-во вопросов, чем хранится в сервисе");
        }
        Set<Question> randomQuestionsSet = new HashSet<>();
        while (randomQuestionsSet.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            randomQuestionsSet.add(randomQuestion);
        }
        return Collections.unmodifiableCollection(randomQuestionsSet);
    }

}

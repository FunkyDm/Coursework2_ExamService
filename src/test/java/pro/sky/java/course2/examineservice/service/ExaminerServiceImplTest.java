package pro.sky.java.course2.examineservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exceptions.RequestLimitExceededException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    private static final Collection<Question> questions = List.of(
            new Question("1", "2"),
            new Question("2", "2"),
            new Question("3", "2")
    );

    @Test
    void getQuestions_shouldReturnRequestLimitExceededException_WhenAmountBiggerThanCollectionSize() {
        when(questionService.getAll()).thenReturn(questions);
        int amount = 4;
        if (amount > questionService.getAll().size()) {
            throw new RequestLimitExceededException("Запрошено большее кол-во вопросов, чем хранится в сервисе");
        }
        assertThrows(RequestLimitExceededException.class, () -> out.getQuestions(amount));
    }

}
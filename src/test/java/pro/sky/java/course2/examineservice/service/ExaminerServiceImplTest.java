package pro.sky.java.course2.examineservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exceptions.RequestLimitExceededException;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl out;


    @Test
    void getQuestions_shouldReturnQuestionsCollection_WhenCollectionIsNotEmpty() {
        Question q1 = new Question("Question 1" , "Answer 1");
        Question q2 = new Question("Question 2" , "Answer 2");
        Set<Question> expected = Set.of(q1,q2);

        when(questionService.getAll()).thenReturn(expected);
        when(questionService.getRandomQuestion()).thenReturn(q1,q2);

        Collection<Question> actual = out.getQuestions(2);

        assertEquals(2,actual.size());

        assertTrue(actual.contains(q1));
        assertTrue(actual.contains(q2));

        verify(questionService, times(1)).getAll();
        verify(questionService, times(2)).getRandomQuestion();
    }

    @Test
    void getQuestions_shouldReturnRequestLimitExceededException_WhenAmountBiggerThanCollectionSize(){
        Set<Question> expected = Set.of(
                new Question("Question 1", "Answer 1")
        );

        when(questionService.getAll()).thenReturn(expected);

        assertThrows(RequestLimitExceededException.class, () -> {
            out.getQuestions(2);
        });
    }

}
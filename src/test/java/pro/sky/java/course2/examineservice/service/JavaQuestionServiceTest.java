package pro.sky.java.course2.examineservice.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exceptions.RequestLimitExceededException;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService out = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource("provideParamsForAddMethod")
    void add(String question, String answer) {
        Question expected = new Question(question, answer);
        Question actual = out.add(question, answer);
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideParamsForAddMethod() {
        return Stream.of(
                Arguments.of("Java question 1", "Java answer 1"),
                Arguments.of("Java question 2", "Java answer 2"),
                Arguments.of("Java question 3", "Java answer 3")
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForRemoveMethod")
    void remove(String question, String answer) {
        Question expected = new Question(question, answer);
        Question actual = out.add(question, answer);
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideParamsForRemoveMethod() {
        return Stream.of(
                Arguments.of("Java question 1", "Java answer 1"),
                Arguments.of("Java question 2", "Java answer 2"),
                Arguments.of("Java question 3", "Java answer 3")
        );
    }

    @Test
    void shouldGetTrueWhenAllQuestionsExists() {
        Question q1 = new Question("Java question 1", "Java answer 1");
        Question q2 = new Question("Java question 2", "Java answer 2");
        out.add(q1);
        out.add(q2);

        Collection<Question> allQuestions = out.getAll();

        assertTrue(allQuestions.contains(q1));
        assertTrue(allQuestions.contains(q2));
    }

    @Test
    void shouldGetAllWhenEmptyCollection(){
        Collection<Question> allQuestions = out.getAll();

        assertTrue(allQuestions.isEmpty());
    }

    @Test
    void shouldReturnSingleQuestionWhenOneElementInCollection() {
        Question q1 = new Question("Java question 1", "Java answer 1");
        out.add(q1);

        assertEquals(q1,out.getRandomQuestion());
    }

//    @Test
//    void shouldReturnNullWhenEmptyCollection() {
//        assertThrows(RequestLimitExceededException.class, out::getRandomQuestion);
//    }



}
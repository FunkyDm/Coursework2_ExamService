package pro.sky.java.course2.examineservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.service.JavaQuestionService;
import pro.sky.java.course2.examineservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService service) {
        this.questionService = service;
    }

    @GetMapping("add")
    Question addQuestion(@RequestParam(value = "question") String question,
                         @RequestParam(value = "answer") String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("remove")
    Question removeQuestion(@RequestParam(value = "question") String question,
                            @RequestParam(value = "answer") String answer) {
        return questionService.remove(question, answer);
    }

    @GetMapping
    Collection<Question> getQuestions() {
        return questionService.getALl();
    }

}

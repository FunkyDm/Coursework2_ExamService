package pro.sky.java.course2.examineservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();

    private final Random ran = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question q = new Question(question, answer);
        questions.remove(q);
        return q;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if(questions.isEmpty()){
            return null;
        }
        return questions.stream()
                .skip(ran.nextInt(0, questions.size()))
                .findFirst()
                .orElse(null);
    }

}

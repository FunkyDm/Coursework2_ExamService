package pro.sky.java.course2.examineservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Запрошено большее кол-во вопросов, чем хранится в сервисе")
public class RequestLimitExceededException extends RuntimeException{
    public RequestLimitExceededException(String message) {
        super(message);
    }
}

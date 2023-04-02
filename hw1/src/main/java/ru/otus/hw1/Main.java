package ru.otus.hw1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.service.FeedbackService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("./spring-context.xml");
        FeedbackService service = ctx.getBean(FeedbackService.class);
        List<Answer> answers = service.collect();
        service.showAnswers(answers);

    }
}

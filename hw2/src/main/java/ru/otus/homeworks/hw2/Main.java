package ru.otus.homeworks.hw2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homeworks.hw2.service.KnowledgeCheckerService;

@ComponentScan
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        KnowledgeCheckerService knowledgeCheckerService = ctx.getBean(KnowledgeCheckerService.class);
        knowledgeCheckerService.run();

    }
}

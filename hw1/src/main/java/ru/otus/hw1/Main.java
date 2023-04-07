package ru.otus.hw1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw1.service.KnowledgeCheckerService;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("./spring-context.xml");
        KnowledgeCheckerService knowledgeCheckerService = ctx.getBean(KnowledgeCheckerService.class);
        knowledgeCheckerService.run();

    }
}

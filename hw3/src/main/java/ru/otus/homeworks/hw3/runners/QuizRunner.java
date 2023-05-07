package ru.otus.homeworks.hw3.runners;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.service.KnowledgeCheckerService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "application.console",
        value = "enabled",
        havingValue = "true"
)
public class QuizRunner implements CommandLineRunner {

    private final KnowledgeCheckerService knowledgeCheckerService;

    @Override
    public void run(String... args) {
        knowledgeCheckerService.run();
    }
}

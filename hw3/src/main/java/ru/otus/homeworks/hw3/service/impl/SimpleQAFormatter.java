package ru.otus.homeworks.hw3.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.service.QAFormatter;

import java.util.stream.IntStream;

@Component
public class SimpleQAFormatter implements QAFormatter {

    @Override
    public String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder();
        builder.append(question.title()).append("\n");
        IntStream.rangeClosed(0, question.options().size() - 1)
                .forEach(i -> builder.append("\t%d - %s".formatted(i + 1, question.options().get(i).title())));
        return builder.toString();
    }

    public String formatAnswer(Answer answer) {
        return "%s - %s%n".formatted(answer.question().title(), answer.answer().isCorrect());
    }

}

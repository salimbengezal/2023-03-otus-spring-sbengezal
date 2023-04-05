package ru.otus.hw1.service.impl;

import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;
import ru.otus.hw1.service.MessageFormatterService;

import java.util.List;
import java.util.stream.IntStream;

public class ConsoleMessageFormatterService implements MessageFormatterService {

    @Override
    public String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder();
        builder.append(question.getTitle()).append("\n");
        IntStream.rangeClosed(0, question.getOptions().size() - 1)
                .forEach(i -> builder.append("\t%d - %s".formatted(i + 1, question.getOptions().get(i).getTitle())));
        return builder.toString();
    }

    private String formatAnswer(Answer answer) {
        return "%s - %s%n".formatted(answer.getQuestion().getTitle(), answer.getAnswer().isCorrect());
    }

    @Override
    public String formatAnswers(List<Answer> answers) {
        StringBuilder builder = new StringBuilder();
        builder.append("Your answers:").append("\n");
        answers.stream().map(this::formatAnswer).forEach(builder::append);
        return builder.toString();
    }

}

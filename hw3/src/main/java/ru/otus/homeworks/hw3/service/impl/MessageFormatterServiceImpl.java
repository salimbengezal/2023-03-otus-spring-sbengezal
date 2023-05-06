package ru.otus.homeworks.hw3.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.MessageFormatterService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class MessageFormatterServiceImpl implements MessageFormatterService {

    @Override
    public String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder();
        builder.append(question.title()).append("\n");
        IntStream.rangeClosed(0, question.options().size() - 1)
                .forEach(i -> builder.append("\t%d - %s".formatted(i + 1, question.options().get(i).title())));
        return builder.toString();
    }

    private String formatAnswer(Answer answer) {
        return "%s - %s%n".formatted(answer.question().title(), answer.answer().isCorrect());
    }

    @Override
    public String formatAnswers(List<Answer> answers) {
        return Stream.concat(Stream.of("Your answers:", "\n"), answers.stream().map(this::formatAnswer))
                .collect(Collectors.joining());
    }

    @Override
    public String formatReport(UserProfile profile, List<Answer> answers, double passingScore) {
        double score = (double) answers.stream().filter(e -> e.answer().isCorrect()).count() / answers.size();

        String roundedScore = String.valueOf(Math.round(score * 100));
        String statusText = score >= passingScore ? "passed" : "not passed";
        return Stream.concat(
                        Stream.of("***********************", "\n",
                                "User tested: ", profile.name(), " ", profile.surname(), "\n",
                                "Your score: ", roundedScore, "%", " (", statusText, ")", "\n",
                                "Your answers:", "\n"),
                        answers.stream().map(this::formatAnswer)
                )
                .collect(Collectors.joining());
    }

}

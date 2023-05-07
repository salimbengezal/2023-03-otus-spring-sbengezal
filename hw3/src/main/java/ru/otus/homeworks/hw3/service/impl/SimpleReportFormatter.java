package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.config.Messages;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.QAFormatter;
import ru.otus.homeworks.hw3.service.ReportFormatter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SimpleReportFormatter implements ReportFormatter {

    private final QAFormatter messageFormatter;

    private final Messages messages;

    @Override
    public String formatMessage(UserProfile profile, List<Answer> answers, double passingScore) {
        double score = (double) answers.stream().filter(e -> e.answer().isCorrect()).count() / answers.size();

        String roundedScore = String.valueOf(Math.round(score * 100));
        String statusText = score >= passingScore ? "result.passed" : "result.not-passed";
        String userLabel = messages.getLocalized("result.user");
        String scoreLabel = messages.getLocalized("result.score");
        String statusLabel = messages.getLocalized(statusText);
        String answersLabel = messages.getLocalized("result.answers");
        return Stream.concat(
                        Stream.of("***********************", "\n",
                                userLabel, ": ", profile.name(), " ", profile.surname(), "\n",
                                scoreLabel, ": ", roundedScore, "%", " (", statusLabel, ")", "\n",
                                answersLabel, ":", "\n"),
                        answers.stream().map(messageFormatter::formatAnswer)
                )
                .collect(Collectors.joining());
    }

}

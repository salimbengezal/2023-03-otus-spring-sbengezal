package ru.otus.homeworks.hw3.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.QAFormatter;
import ru.otus.homeworks.hw3.service.ReportFormatter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SimpleReportFormatter implements ReportFormatter {

    private final QAFormatter messageFormatter;

    public SimpleReportFormatter(QAFormatter messageFormatter) {
        this.messageFormatter = messageFormatter;
    }

    @Override
    public String formatMessage(UserProfile profile, List<Answer> answers, double passingScore) {
        double score = (double) answers.stream().filter(e -> e.answer().isCorrect()).count() / answers.size();

        String roundedScore = String.valueOf(Math.round(score * 100));
        String statusText = score >= passingScore ? "passed" : "not passed";
        return Stream.concat(
                        Stream.of("***********************", "\n",
                                "User tested: ", profile.name(), " ", profile.surname(), "\n",
                                "Your score: ", roundedScore, "%", " (", statusText, ")", "\n",
                                "Your answers:", "\n"),
                        answers.stream().map(messageFormatter::formatAnswer)
                )
                .collect(Collectors.joining());
    }

}

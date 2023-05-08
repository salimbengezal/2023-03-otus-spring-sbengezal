package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.LocalizationService;
import ru.otus.homeworks.hw3.service.ReportFormatter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SimpleReportFormatter implements ReportFormatter {

    private final LocalizationService localizationService;

    @Override
    public String formatMessage(UserProfile profile, List<Answer> answers, double passingScore) {
        double score = (double) answers.stream().filter(e -> e.selectedOption().isCorrect()).count() / answers.size();

        String roundedScore = String.valueOf(Math.round(score * 100));
        String statusKey = score >= passingScore ? Messages.RESULT_PASSED : Messages.RESULT_NOT_PASSED;
        String userLabel = localizationService.getMessage(Messages.RESULT_USER);
        String scoreLabel = localizationService.getMessage(Messages.RESULT_SCORE);
        String statusLabel = localizationService.getMessage(statusKey);
        String answersLabel = localizationService.getMessage(Messages.RESULT_ANSWERS);
        return Stream.concat(
                        Stream.of("***********************", "\n",
                                userLabel, ": ", profile.name(), " ", profile.surname(), "\n",
                                scoreLabel, ": ", roundedScore, "%", " (", statusLabel, ")", "\n",
                                answersLabel, ":", "\n"),
                        answers.stream().map(this::formatAnswer)
                )
                .collect(Collectors.joining());
    }

    private String formatAnswer(Answer answer) {
        String key = answer.selectedOption().isCorrect() ? Messages.QUESTION_CORRECT : Messages.QUESTIONS_INCORRECT;
        return "%s - %s%n".formatted(answer.question().title(), localizationService.getMessage(key));
    }

}

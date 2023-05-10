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

    public static final String RESULT_USER = "result.user";

    public static final String RESULT_SCORE = "result.score";

    public static final String RESULT_ANSWERS = "result.answers";

    public static final String RESULT_PASSED = "result.passed";

    public static final String RESULT_NOT_PASSED = "result.not-passed";

    public static final String QUESTION_CORRECT = "questions.correct";

    public static final String QUESTIONS_INCORRECT = "questions.incorrect";


    @Override
    public String formatMessage(UserProfile profile, List<Answer> answers, double passingScore) {
        double score = (double) answers.stream().filter(e -> e.selectedOption().isCorrect()).count() / answers.size();

        String roundedScore = String.valueOf(Math.round(score * 100));
        String statusKey = score >= passingScore ? RESULT_PASSED : RESULT_NOT_PASSED;
        String userLabel = localizationService.getMessage(RESULT_USER);
        String scoreLabel = localizationService.getMessage(RESULT_SCORE);
        String statusLabel = localizationService.getMessage(statusKey);
        String answersLabel = localizationService.getMessage(RESULT_ANSWERS);
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
        String key = answer.selectedOption().isCorrect() ? QUESTION_CORRECT : QUESTIONS_INCORRECT;
        return "%s - %s%n".formatted(answer.question().title(), localizationService.getMessage(key));
    }

}

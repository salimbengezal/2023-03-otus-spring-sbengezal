package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.QuestionOption;
import ru.otus.homeworks.hw3.service.IOLocalizationService;
import ru.otus.homeworks.hw3.service.QuestionerService;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SimpleQuestionerService implements QuestionerService {

    public static final String ASK_WRONG_REPEAT = "ask.wrong-repeat";

    public static final String ASK_NOT_PARSEABLE = "ask.not-parseable";

    public static final String QUESTION_MESSAGE = "questions.message";

    private final IOLocalizationService ioLocalizationService;

    private Answer getAnswerByQuestion(Question question) {
        int answer = -1;
        boolean isValidAnswer;
        do {
            try {
                answer = ioLocalizationService.readInt();
            } catch (NumberFormatException ignored) {
                ioLocalizationService.showMessageByKey(true, ASK_NOT_PARSEABLE);
            }
            isValidAnswer = answer >= 1 && answer <= question.options().size();
            if (!isValidAnswer) {
                ioLocalizationService.showMessageByKey(true, ASK_WRONG_REPEAT);
            }
        } while (!isValidAnswer);
        QuestionOption answerOption = question.options().get(answer - 1);
        return new Answer(question, answerOption);
    }

    public List<Answer> getAnswersByQuestions(List<Question> questions) {
        ioLocalizationService.showMessageByKey(true, "%s:", QUESTION_MESSAGE);
        return questions.stream().map(question -> {
            String questionText = formatQuestion(question);
            ioLocalizationService.showText(questionText);
            return getAnswerByQuestion(question);
        }).toList();
    }

    private String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder();
        builder.append(question.title()).append("\n");
        IntStream.rangeClosed(0, question.options().size() - 1)
                .forEach(i -> builder.append("\t%d - %s".formatted(i + 1, question.options().get(i).title())));
        return builder.toString();
    }

}

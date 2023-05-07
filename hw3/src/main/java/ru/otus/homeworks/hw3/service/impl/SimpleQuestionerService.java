package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.config.Messages;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.QuestionOption;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.QAFormatter;
import ru.otus.homeworks.hw3.service.QuestionerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleQuestionerService implements QuestionerService {

    private final IOService ioService;

    private final Messages messages;

    private final QAFormatter qaFormatter;

    private Answer getAnswerByQuestion(Question question) {
        int answer = -1;
        boolean isValidAnswer;
        do {
            try {
                answer = ioService.readInt();
            } catch (NumberFormatException ignored) {
                String notParseableAnswerMessage = messages.getLocalized("ask.not-parseable");
                ioService.showMessageInline(notParseableAnswerMessage);
            }
            isValidAnswer = answer >= 1 && answer <= question.options().size();
            if (!isValidAnswer) {
                String wrongRepeatMessage = messages.getLocalized("ask.wrong-repeat");
                ioService.showMessageInline(wrongRepeatMessage);
            }
        } while (!isValidAnswer);
        QuestionOption answerOption = question.options().get(answer - 1);
        return new Answer(question, answerOption);
    }

    public List<Answer> getAnswersByQuestions(List<Question> questions) {
        String message = messages.getLocalized("questions.message");
        ioService.showMessageInline("%s:".formatted(message));
        return questions.stream().map(question -> {
            String questionText = qaFormatter.formatQuestion(question);
            ioService.showMessageInline(questionText);
            return getAnswerByQuestion(question);
        }).toList();
    }
}

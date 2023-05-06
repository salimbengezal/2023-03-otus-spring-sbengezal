package ru.otus.homeworks.hw2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw2.domain.Answer;
import ru.otus.homeworks.hw2.domain.Question;
import ru.otus.homeworks.hw2.domain.QuestionOption;
import ru.otus.homeworks.hw2.service.IOService;
import ru.otus.homeworks.hw2.service.QuestionerService;

@Service
@RequiredArgsConstructor
public class SimpleQuestionerService implements QuestionerService {

    private final IOService ioService;

    @Override
    public Answer getAnswerByQuestion(Question question) {
        int answer = -1;
        boolean isValidAnswer;
        do {
            try {
                answer = ioService.readInt();
            } catch (NumberFormatException ignored) {
                ioService.showMessageInline("Not parseable answer");
            }
            isValidAnswer = answer >= 1 && answer <= question.options().size();
            if (!isValidAnswer) {
                ioService.showMessageInline("Wrong answer. Please repeat");
            }
        } while (!isValidAnswer);
        QuestionOption answerOption = question.options().get(answer - 1);
        return new Answer(question, answerOption);
    }
}

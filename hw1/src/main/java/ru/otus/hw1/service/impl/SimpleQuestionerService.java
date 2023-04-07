package ru.otus.hw1.service.impl;

import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;
import ru.otus.hw1.domain.QuestionOption;
import ru.otus.hw1.service.IOService;
import ru.otus.hw1.service.QuestionerService;

public class SimpleQuestionerService implements QuestionerService {

    private final IOService ioService;

    public SimpleQuestionerService(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Answer getAnswerByQuestion(Question question) {
        int answer = -1;
        boolean isValidAnswer;
        do {
            try {
                answer = ioService.readInt();
            } catch (NumberFormatException ignored) {
                ioService.showMessage("Not parseable answer");
            }
            isValidAnswer = answer >= 1 && answer <= question.getOptions().size();
            if (!isValidAnswer) {
                ioService.showMessage("Wrong answer. Please repeat");
            }
        } while (!isValidAnswer);
        QuestionOption answerOption = question.getOptions().get(answer - 1);
        return new Answer(question, answerOption);
    }
}

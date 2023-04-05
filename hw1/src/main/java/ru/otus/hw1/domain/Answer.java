package ru.otus.hw1.domain;

public class Answer {

    private final Question question;

    private final QuestionOption answer;

    public Answer(Question question, QuestionOption answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public QuestionOption getAnswer() {
        return answer;
    }
}

package ru.otus.hw1.domain;

public class Answer {

    private final Question question;

    private final int position;

    public Answer(Question question, int position) {
        this.question = question;
        this.position = position;
    }

    public Question getQuestion() {
        return question;
    }

    public int getPosition() {
        return position;
    }
}

package ru.otus.hw1.domain;

public class QuestionOption {
    private final String title;

    private final Boolean isCorrect;

    public QuestionOption(String title, Boolean isCorrect) {
        this.title = title;
        this.isCorrect = isCorrect;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isCorrect() {
        return isCorrect;
    }
}

package ru.otus.hw1.domain;

public class QuestionOption {
    private final String title;

    private final boolean isCorrect;

    public QuestionOption(String title, boolean isCorrect) {
        this.title = title;
        this.isCorrect = isCorrect;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}

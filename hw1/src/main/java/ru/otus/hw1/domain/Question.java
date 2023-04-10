package ru.otus.hw1.domain;

import java.util.List;

public class Question {

    private final String title;

    private final List<QuestionOption> options;

    public Question(String title, List<QuestionOption> options) {
        this.title = title;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }
}

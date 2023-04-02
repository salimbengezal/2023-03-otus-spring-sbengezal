package ru.otus.hw1.domain;

import java.util.List;

public class Question {

    private final String title;

    private final List<String> options;

    public Question(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getTitle() {
        return title;
    }
}

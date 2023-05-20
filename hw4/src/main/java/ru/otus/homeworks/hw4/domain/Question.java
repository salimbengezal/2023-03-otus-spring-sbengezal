package ru.otus.homeworks.hw4.domain;

import java.util.List;

public record Question(String title, List<QuestionOption> options) {
}

package ru.otus.homeworks.hw2.domain;

import java.util.List;

public record Question(String title, List<QuestionOption> options) {
}

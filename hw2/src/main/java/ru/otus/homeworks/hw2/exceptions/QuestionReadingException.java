package ru.otus.homeworks.hw2.exceptions;

public class QuestionReadingException extends RuntimeException {

    public QuestionReadingException(String message, Throwable err) {
        super(message, err);
    }

}

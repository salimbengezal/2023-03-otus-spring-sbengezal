package ru.otus.homeworks.hw4.exceptions;

public class QuestionReadingException extends RuntimeException {

    public QuestionReadingException(String message, Throwable err) {
        super(message, err);
    }

}

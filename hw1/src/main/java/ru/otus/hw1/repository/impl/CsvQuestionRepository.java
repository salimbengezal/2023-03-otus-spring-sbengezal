package ru.otus.hw1.repository.impl;

import ru.otus.hw1.domain.Question;
import ru.otus.hw1.domain.QuestionOption;
import ru.otus.hw1.exceptions.NotEnoughElementsException;
import ru.otus.hw1.exceptions.QuestionFileNotFoundException;
import ru.otus.hw1.exceptions.QuestionReadingException;
import ru.otus.hw1.repository.QuestionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CsvQuestionRepository implements QuestionRepository {

    private final String fileName;

    private final String delimiter;

    public CsvQuestionRepository(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
    }

    @Override
    public List<Question> getAll() throws QuestionReadingException {
        return readLinesFromFile().stream()
                .map(line -> line.split(delimiter))
                .map(this::getQuestion)
                .toList();
    }

    private Question getQuestion(String[] array) {
        if (array.length <= 2) {
            throw new NotEnoughElementsException();
        }
        List<QuestionOption> options = IntStream.rangeClosed(1, array.length - 1)
                .mapToObj(i -> new QuestionOption(array[i], i == 1))
                .toList();
        List<QuestionOption> shuffledOptions = new ArrayList<>(options);
        Collections.shuffle(shuffledOptions);
        return new Question(array[0], shuffledOptions);
    }

    private List<String> readLinesFromFile() throws QuestionFileNotFoundException, QuestionReadingException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new QuestionFileNotFoundException("File with questions not found");
        }
        List<String> lines = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new QuestionReadingException(e.getMessage(), e);
        }
        return lines;
    }

}

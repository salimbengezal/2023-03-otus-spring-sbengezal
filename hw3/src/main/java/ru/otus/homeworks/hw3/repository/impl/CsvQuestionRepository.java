package ru.otus.homeworks.hw3.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homeworks.hw3.config.QuestionFileProperties;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.QuestionOption;
import ru.otus.homeworks.hw3.exceptions.NotEnoughElementsException;
import ru.otus.homeworks.hw3.exceptions.QuestionFileNotFoundException;
import ru.otus.homeworks.hw3.exceptions.QuestionReadingException;
import ru.otus.homeworks.hw3.repository.QuestionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Repository
@RequiredArgsConstructor
public class CsvQuestionRepository implements QuestionRepository {

    private final QuestionFileProperties properties;

    @Override
    public List<Question> getAll() {
        String delimiter = properties.getDelimiter();
        try {
            return readLinesFromFile().stream()
                    .map(line -> line.split(delimiter))
                    .map(this::getQuestion)
                    .toList();
        } catch (Exception e) {
            throw new QuestionReadingException(e.getMessage(), e);
        }
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
        String fileName = properties.getFileName();
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

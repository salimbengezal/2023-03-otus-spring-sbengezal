package ru.otus.homeworks.hw2.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.homeworks.hw2.domain.Question;
import ru.otus.homeworks.hw2.domain.QuestionOption;
import ru.otus.homeworks.hw2.exceptions.NotEnoughElementsException;
import ru.otus.homeworks.hw2.exceptions.QuestionFileNotFoundException;
import ru.otus.homeworks.hw2.exceptions.QuestionReadingException;
import ru.otus.homeworks.hw2.repository.QuestionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@PropertySource("classpath:application.properties")
@Repository
public class CsvQuestionRepository implements QuestionRepository {

    private final String fileName;

    private final String delimiter;

    public CsvQuestionRepository(@Value("${questions.file.name}") String fileName,
                                 @Value("${questions.file.delimiter}") String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
    }

    @Override
    public List<Question> getAll() throws QuestionReadingException {
        try {
            return readLinesFromFile().stream()
                    .map(line -> line.split(delimiter))
                    .map(this::getQuestion)
                    .toList();
        } catch (Exception e) {
            throw new QuestionReadingException(e.getMessage(), e.getCause());
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

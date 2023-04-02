package ru.otus.hw1.repository;

import ru.otus.hw1.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionRepositoryCsv implements QuestionRepository {

    private final String fileName;

    private final String delimiter;

    public QuestionRepositoryCsv(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
    }

    @Override
    public List<Question> getAll() {
        List<String[]> strings = readLinesFromFile().stream().map(line -> line.split(delimiter)).toList();
        return strings.stream().map(list -> new Question(list[0], Arrays.stream(list).skip(1).toList())).toList();
    }

    private List<String> readLinesFromFile() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        List<String> lines = new ArrayList<>();
        try {
            assert is != null;
            try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return lines;
    }

}

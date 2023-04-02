package ru.otus.hw1.service;

import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;
import ru.otus.hw1.repository.QuestionRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FeedbackServiceSimple implements FeedbackService {

    private final QuestionRepository repository;

    public FeedbackServiceSimple(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Answer> collect() {
        List<Question> questions = repository.getAll();
        checkQuestionOptions(questions);
        Scanner scanner = new Scanner(System.in);
        return questions.stream()
                .map(question -> getAnswer(question, scanner))
                .collect(Collectors.toList());
    }

    private void checkQuestionOptions(List<Question> questions) {
        if (!questions.stream().filter(q -> q.getOptions().size() < 2).toList().isEmpty())
            throw new RuntimeException("Question doesn't have choice");
    }

    public void showAnswers(List<Answer> answers) {
        System.out.println("Your answers:");
        answers.forEach(answer -> {
            String title = answer.getQuestion().getTitle();
            String option = answer.getQuestion().getOptions().get(answer.getPosition());
            System.out.println(title + " - " + option);
        });
    }

    private Answer getAnswer(Question question, Scanner scanner) {
        System.out.println(question.getTitle());
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, question.getOptions().size())
                .forEach(i -> builder.append("\t").append(i + 1).append(" - ").append(question.getOptions().get(i)));
        System.out.println(builder);
        int answer = -1;
        boolean isValidAnswer;
        do {
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
            isValidAnswer = answer >= 1 && answer <= question.getOptions().size();
            if (!isValidAnswer) {
                System.out.println("Wrong answer. Please repeat");
            }
        } while (!isValidAnswer);
        return new Answer(question, answer - 1);
    }

}

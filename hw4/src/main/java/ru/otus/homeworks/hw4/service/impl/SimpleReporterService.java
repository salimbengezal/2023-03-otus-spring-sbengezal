package ru.otus.homeworks.hw4.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw4.config.QuizProperties;
import ru.otus.homeworks.hw4.domain.Answer;
import ru.otus.homeworks.hw4.domain.UserProfile;
import ru.otus.homeworks.hw4.service.IOService;
import ru.otus.homeworks.hw4.service.ReportFormatter;
import ru.otus.homeworks.hw4.service.ReporterService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleReporterService implements ReporterService {

    private final ReportFormatter reportFormatter;

    private final QuizProperties properties;

    private final IOService ioService;

    public void showReport(UserProfile profile, List<Answer> answers) {
        String reportText = reportFormatter.formatMessage(profile, answers, properties.getPassingScore());
        ioService.showMessage(true, reportText);
    }

}

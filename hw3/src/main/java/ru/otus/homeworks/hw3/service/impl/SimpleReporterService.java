package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.config.AppProperties;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.ReportFormatter;
import ru.otus.homeworks.hw3.service.ReporterService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleReporterService implements ReporterService {

    private final ReportFormatter reportFormatter;

    private final AppProperties properties;

    private final IOService ioService;

    public void showReport(UserProfile profile, List<Answer> answers) {
        String reportText = reportFormatter.formatMessage(profile, answers, properties.getPassingScore());
        ioService.showMessageInline(reportText);
    }

}

package ru.otus.homeworks.hw4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homeworks.hw4.domain.Answer;
import ru.otus.homeworks.hw4.domain.UserProfile;
import ru.otus.homeworks.hw4.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw4.service.ReporterService;
import ru.otus.homeworks.hw4.service.UserProfileService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class QuizShellCommands {

    private final UserProfileService profileService;

    private final KnowledgeCheckerService quizService;

    private final ReporterService reporterService;

    private UserProfile profile;

    private List<Answer> answers;

    @ShellMethod(value = "Start testing", key = {"start", "run"})
    @ShellMethodAvailability(value = "isLoggedIn")
    public void start() {
        answers = quizService.run();
        showLastResults();
    }

    @ShellMethod(value = "Sign in or change User", key = {"login", "auth"})
    public void login() {
        profile = profileService.getProfile();
    }

    @ShellMethodAvailability(value = "isAlreadyTested")
    @ShellMethod(value = "Show last result", key = {"results", "show"})
    public void showLastResults() {
        reporterService.showReport(profile, answers);
    }

    private Availability isLoggedIn() {
        return profile == null
                ? Availability.unavailable("You can't start testing until you sign in")
                : Availability.available();
    }

    private Availability isAlreadyTested() {
        return answers == null
                ? Availability.unavailable("You can't show results until you sign in")
                : Availability.available();
    }

}

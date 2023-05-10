package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.config.LocaleProperties;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.LocalizationService;
import ru.otus.homeworks.hw3.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class SimpleUserProfileService implements UserProfileService {

    private final LocalizationService localizationService;

    private final IOService ioService;

    public static final String ASK_NAME = "ask.name";

    public static final String ASK_SURNAME = "ask.surname";

    private final LocaleProperties properties;

    @Override
    public UserProfile getProfile() {
        String localizedName = localizationService.getMessage(ASK_NAME);
        String localizedNameMessage = "%s:".formatted(localizedName);
        ioService.showMessage(false, localizedNameMessage);
        String name = ioService.readString();
        String localizedSurName = localizationService.getMessage(ASK_SURNAME);
        String localizedSurnameMessage = "%s:".formatted(localizedSurName);
        ioService.showMessage(false, localizedSurnameMessage);
        String surname = ioService.readString();
        return new UserProfile(name, surname);
    }
}

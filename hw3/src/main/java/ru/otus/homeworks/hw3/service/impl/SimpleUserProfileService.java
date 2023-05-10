package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.IOLocalizationService;
import ru.otus.homeworks.hw3.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class SimpleUserProfileService implements UserProfileService {

    public static final String ASK_NAME = "ask.name";

    public static final String ASK_SURNAME = "ask.surname";

    private final IOLocalizationService ioLocalizationService;

    @Override
    public UserProfile getProfile() {
        ioLocalizationService.showMessageByKey(false, "%s:", ASK_NAME);
        String name = ioLocalizationService.readString();
        ioLocalizationService.showMessageByKey(false, "%s:", ASK_SURNAME);
        String surname = ioLocalizationService.readString();
        return new UserProfile(name, surname);
    }
}

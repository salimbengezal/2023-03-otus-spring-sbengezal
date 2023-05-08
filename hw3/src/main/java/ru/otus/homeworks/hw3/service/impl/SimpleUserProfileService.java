package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.LocalizationService;
import ru.otus.homeworks.hw3.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class SimpleUserProfileService implements UserProfileService {

    private final LocalizationService localizationService;

    private final IOService ioService;

    @Override
    public UserProfile getProfile() {
        String localizedName = localizationService.getMessage(Messages.ASK_NAME);
        String localizedNameMessage = "%s:".formatted(localizedName);
        ioService.showMessage(false, localizedNameMessage);
        String name = ioService.readString();
        String localizedSurName = localizationService.getMessage(Messages.ASK_SURNAME);
        String localizedSurnameMessage = "%s:".formatted(localizedSurName);
        ioService.showMessage(false, localizedSurnameMessage);
        String surname = ioService.readString();
        return new UserProfile(name, surname);
    }
}

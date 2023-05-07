package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.config.Messages;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.UserProfileService;

@Service
@RequiredArgsConstructor
public class SimpleUserProfileService implements UserProfileService {

    private final Messages messages;

    private final IOService ioService;

    @Override
    public UserProfile getProfile() {
        String labelName = messages.getLocalized("ask.name");
        ioService.showMessage("%s:".formatted(labelName));
        String name = ioService.readString();
        String labelSurname = messages.getLocalized("ask.surname");
        ioService.showMessage("%s:".formatted(labelSurname));
        String surname = ioService.readString();
        return new UserProfile(name, surname);
    }
}

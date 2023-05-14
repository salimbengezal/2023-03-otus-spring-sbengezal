package ru.otus.homeworks.hw4.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw4.service.IOLocalizationService;
import ru.otus.homeworks.hw4.service.IOService;
import ru.otus.homeworks.hw4.service.LocalizationService;

@Service
@RequiredArgsConstructor
public class IOLocalizationServiceImpl implements IOLocalizationService {

    private final IOService ioService;

    private final LocalizationService localizationService;

    @Override
    public void showMessageByKey(boolean inline, String pattern, String key) {
        String value = localizationService.getMessage(key);
        String formatted = pattern != null ? pattern.formatted(value) : value;
        showText(inline, formatted);
    }

    @Override
    public void showMessageByKey(boolean inline, String key) {
        showMessageByKey(inline, null, key);
    }

    @Override
    public void showText(String message) {
        showText(true, message);
    }

    public void showText(boolean inline, String message) {
        ioService.showMessage(inline, message);
    }

    @Override
    public String readString() {
        return ioService.readString();
    }

    @Override
    public int readInt() {
        return ioService.readInt();
    }
}

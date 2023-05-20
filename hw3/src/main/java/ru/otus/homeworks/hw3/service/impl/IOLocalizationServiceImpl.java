package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.service.IOLocalizationService;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.LocalizationService;

@Service
@RequiredArgsConstructor
public class IOLocalizationServiceImpl implements IOLocalizationService {

    private final IOService ioService;

    private final LocalizationService localizationService;

    @Override
    public void showMessageByKey(boolean inline, String key) {
        showText(inline, localizationService.getMessage(key));
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

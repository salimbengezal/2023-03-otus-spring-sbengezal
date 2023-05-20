package ru.otus.homeworks.hw3.service.impl;

import ru.otus.homeworks.hw3.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService {

    private final PrintStream out;

    private final Scanner scanner;

    public IOServiceStreams(PrintStream out, InputStream in) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    @Override
    public void showMessage(boolean inline, String text) {
        if (inline) {
            out.println(text);
        } else {
            out.print(text);
        }
    }

    @Override
    public int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

}

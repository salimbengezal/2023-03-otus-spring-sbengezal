package ru.otus.hw1.service.impl;

import ru.otus.hw1.service.IOService;

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

    public IOServiceStreams() {
        this.out = System.out;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showMessage(String text) {
        out.println(text);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

}

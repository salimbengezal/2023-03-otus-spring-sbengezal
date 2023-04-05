package ru.otus.hw1.service.impl;

import ru.otus.hw1.service.IOStreamService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOStreamService implements IOStreamService {

    private final PrintStream out;

    private final Scanner scanner;

    public ConsoleIOStreamService(PrintStream out, InputStream in) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public ConsoleIOStreamService() {
        this.out = System.out;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showMessage(String text) {
        out.println(text);
    }

    @Override
    public Integer readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

}

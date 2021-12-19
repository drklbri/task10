package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.utils.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(Locale.ROOT);
        if (args.length == 0) {
            try {
                WindowVersion.runInWindowMode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ConsoleVersion.runInCommandLineMode(args);
        }
    }
}

package ru.vsu.cs.shevchenko_daniil;

import java.io.FileNotFoundException;
import java.util.Locale;

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

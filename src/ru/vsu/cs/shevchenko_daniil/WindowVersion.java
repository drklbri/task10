package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.utils.SwingUtils;

import javax.swing.*;
import java.util.Locale;

public class WindowVersion {

    public static void runInWindowMode() throws Exception {
        SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Table().setVisible(true));
    }
}
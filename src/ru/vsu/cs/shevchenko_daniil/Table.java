package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.utils.JTableUtils;
import ru.vsu.cs.shevchenko_daniil.utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Table extends JFrame {
    private JButton loadButton;
    private JTextArea numberOfTriangleField;
    private JButton solutionButton;
    private JTextArea trianglesLyingInQuartersArea;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton callTriangleButton;
    private JTable table1;
    private JButton saveTriangleButton;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private final JMenuBar menuBarMain;
    private final JMenu menuLookAndFeel;
    private List<Triangle> inputTriangleList = new ArrayList<>();
    private List<Triangle> outputTriangleList = new ArrayList<>();

    public Table() {
        this.setTitle("Table");
        this.setContentPane(mainPanel);
        JTableUtils.initJTableForArray(table1, 40, true, true, false, false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        this.pack();

        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder trianglesInOneQuarter = new StringBuilder("Полностю в одной четверти лежат треугольники: ");
                int counter = 0;
                for (Triangle triangle : inputTriangleList) {
                    counter++;
                    if (Task.isTriangleCompleteInQuarter(triangle)) {
                        if (counter < inputTriangleList.size()-1) {
                            trianglesInOneQuarter.append(counter).append(", ");
                        } else trianglesInOneQuarter.append(counter).append(".");
                        outputTriangleList.add(triangle);
                    }
                }
                trianglesLyingInQuartersArea.setText(trianglesInOneQuarter.toString());
            }
        });

        loadButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    inputTriangleList = Task.createTrianglesFromFile(fileChooserOpen.getSelectedFile().getPath());
                    double[][] inputDoubleArray = Task.trianglesListToDoubleArray2(inputTriangleList);
                }
            } catch (Exception a) {
                SwingUtils.showErrorMessageBox(a);
            }
        });

        saveButton.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    Task.writeTriangleListToFile(file, outputTriangleList);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
        callTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfTriangle = Integer.parseInt(numberOfTriangleField.getText());
                    double[] tempArray = Task.triangleIntoDoubleArray(inputTriangleList.get(numberOfTriangle - 1));
                    JTableUtils.writeArrayToJTable(table1, tempArray);
                } catch (NumberFormatException ex) {
                    numberOfTriangleField.setText("Введите целое число");
                } catch (IndexOutOfBoundsException ex) {
                    numberOfTriangleField.setText("Треугольника с таким номером не существует, либо файл не был загружен.");
                }
            }
        });
        saveTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfTriangle = Integer.parseInt(numberOfTriangleField.getText());
                    double[] tempArray = JTableUtils.readDoubleArrayFromJTable(table1);
                    inputTriangleList.set(numberOfTriangle-1, Task.createTriangleOutOfPoints(Task.createPointsList(tempArray)));
                } catch (NumberFormatException | ParseException ex) {
                    numberOfTriangleField.setText("Введите целое число");
                }
            }
        });
    }
}

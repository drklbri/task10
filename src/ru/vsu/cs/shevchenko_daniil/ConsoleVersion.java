package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.utils.ArrayUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleVersion {

    public static void runInCommandLineMode(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        ArgsParser argsParser = new ArgsParser(args);
        String inputFilePath = argsParser.getArgumentValue("-i", "--input-file");
        String outputFilePath = argsParser.getArgumentValue("-o", "--output-file");

        System.out.println("Загрузить даннные из файла или из консоли? ('Да' консоли и 'Нет' для чтения из файла)");
        String nextLine = sc.nextLine();
        if (nextLine.equals("нет") || nextLine.equals("Нет")) {
            List<Triangle> triangleList = Task.createTrianglesFromFile(inputFilePath);
            System.out.println("Всего загружено треугольников: " + triangleList.size());

            List<Triangle> tempList = new ArrayList<>();
            for (Triangle triangle : triangleList) {
                if (Task.isTriangleCompleteInQuarter(triangle)) {
                    tempList.add(triangle);
                }
            }

            Task.writeTriangleListToFile(outputFilePath, tempList);
            System.out.println("Треугольники обработаны и успешно сохранены");
        } else if (nextLine.equals("да") || nextLine.equals("Да")) {
            System.out.println("Введите 6 координат треугольника. Завершите ввод числом 42.");
            List<Triangle> triangleList = new ArrayList<>();



            while (!sc.nextLine().equals("42")) {
                Triangle triangle = Task.createTriangleOutOfPoints(Task.createPointsList(ArrayUtils.readDoubleArrayFromConsole()));
                if (Task.isTriangleCompleteInQuarter(triangle)) triangleList.add(triangle);
            }
            if (triangleList.size()!= 0) {
                Task.writeTriangleListToFile(outputFilePath, triangleList);
                System.out.println("Треугольники обработаны и сохранены в файл");
            } else {
                System.out.println("Ни одного треугольника? Ну ладно.");
            }
        } else System.out.println("Некорректный ввод");


    }
}

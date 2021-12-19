package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.utils.ArrayUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class Task {

    public static int quarterOfPoint(Point point) {
        if (point.getX() > 0 && point.getY() > 0)
            return 1;
        if (point.getX() < 0 && point.getY() > 0)
            return 2;
        if (point.getX() < 0 && point.getY() < 0)
            return 3;
        if (point.getX() > 0 && point.getY() < 0)
            return 4;
        return -1;
    }

    public static boolean isTriangleCompleteInQuarter(Triangle triangle) {
        return quarterOfPoint(triangle.getP1()) == quarterOfPoint(triangle.getP2()) && quarterOfPoint(triangle.getP2()) == quarterOfPoint(triangle.getP3());
    }


    public static Triangle createTriangleOutOfPoints(List<Point> pointList) {

        return new Triangle(pointList.get(0), pointList.get(1), pointList.get(2));
    }

    public static Point createPoint(double x, double y) {
        return new Point(x, y);
    }

    public static List<Point> createPointsList(double[] pointsCords) {
        Point p1 = createPoint(pointsCords[0], pointsCords[1]);
        Point p2 = createPoint(pointsCords[2], pointsCords[3]);
        Point p3 = createPoint(pointsCords[4], pointsCords[5]);

        List<Point> pointList = new ArrayList<>(3);
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);


        return pointList;
    }

    public static List<Triangle> createTrianglesFromFile(String fileName) throws FileNotFoundException {
        List<Triangle> triangleList = new ArrayList<>();

        double[][] coordArray2 = ArrayUtils.readDoubleArray2FromFile(fileName);

        for (int i = 0; i < Objects.requireNonNull(coordArray2).length; i++) {
            triangleList.add(createTriangleOutOfPoints(createPointsList(coordArray2[i])));
        }

        return triangleList;
    }

    public static double[][] trianglesListToDoubleArray2(List<Triangle> trianglesList) {
        double[][] trianglesDoubleArray = new double[trianglesList.size()][6];
        int r = 0;
        for (Triangle triangle : trianglesList) {
            trianglesDoubleArray[r][0] = triangle.getP1().getX();
            trianglesDoubleArray[r][1] = triangle.getP1().getY();
            trianglesDoubleArray[r][2] = triangle.getP2().getX();
            trianglesDoubleArray[r][3] = triangle.getP2().getY();
            trianglesDoubleArray[r][4] = triangle.getP3().getX();
            trianglesDoubleArray[r][5] = triangle.getP3().getY();
            r++;
        }
        return trianglesDoubleArray;
    }

    public static void writeTriangleListToFile(String filePath, List<Triangle> triangleList) throws FileNotFoundException {
        ArrayUtils.writeArrayToFile(filePath, trianglesListToDoubleArray2(triangleList));
    }

    public static double[] triangleIntoDoubleArray(Triangle triangle) {
        double[] trianglesDoubleArray = new double[6];
        trianglesDoubleArray[0] = triangle.getP1().getX();
        trianglesDoubleArray[1] = triangle.getP1().getY();
        trianglesDoubleArray[2] = triangle.getP2().getX();
        trianglesDoubleArray[3] = triangle.getP2().getY();
        trianglesDoubleArray[4] = triangle.getP3().getX();
        trianglesDoubleArray[5] = triangle.getP3().getY();

        return trianglesDoubleArray;
    }
}
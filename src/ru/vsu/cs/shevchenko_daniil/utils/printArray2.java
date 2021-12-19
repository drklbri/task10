package ru.vsu.cs.shevchenko_daniil;

public class printArray2 {

    public static void outArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.print(arr[i]);
            }
        }
    }


    public static void outArray2(int[][] arr2) {
        for (int[] ints : arr2) {
            if (ints == null) {
                System.out.println("null");
            } else {
                for (int c = 0; c < ints.length; c++) {
                    System.out.print((c > 0 ? ", " : "") + ints[c]);
                }
                System.out.println();
            }
        }
    }
}
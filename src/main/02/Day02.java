package main._02;

import java.io.File;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;

import util.FileConsumer;

public class Day02 {
    public static void main(String[] args) {
        System.out.println("advent day 02");

        ClassLoader classLoader = Day02.class.getClassLoader();
        File inputFile = new File(classLoader.getResource("src/main/resources/input/02/input.txt").getFile());
        int[] safeLineCounter = {0};

        Consumer<String> inputToLine = line -> {
            List<Long> lineValues = new ArrayList<>(
                Arrays.stream(line.trim().split("\\s+"))
                    .map(Long::parseLong)
                    .toList()
            );

            boolean isLineSafe = isLineSafe(lineValues);
            if (isLineSafe) {
                safeLineCounter[0]++;
            }
            System.out.println(safeLineCounter[0]);
        };

        FileConsumer fileConsumer = new FileConsumer();
        fileConsumer.consumeFile(inputFile, inputToLine);

        System.out.println(safeLineCounter[0]);
    
    }
    private static boolean isValidIncrease(List<Long> lineValues) {
        long firstElement = lineValues.remove(0);
        long diff = lineValues.get(0) - firstElement;
        System.out.println("incr :" + firstElement);
        System.out.println("incr+:" + lineValues.get(0));

        if (diff > 0 && diff < 4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidDecrease(List<Long> lineValues) {
        long firstElement = lineValues.remove(0);
        long diff = lineValues.get(0) - firstElement;
        System.out.println("decr :" + firstElement);
        System.out.println("decr+:" + lineValues.get(0));

        if (diff < 0 && diff > -4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isLineSafe(List<Long> lineValues) {
        boolean isLineSafe = false;
        boolean isIncrease = false;
        boolean isValidIncrease = false;
        boolean isDecrease = false;
        boolean isValidDecrease = false;
        if (lineValues.size() > 1) {
            long firstDiff = lineValues.get(1) - lineValues.get(0);
            if (firstDiff == 0) {
                return false;
            } else if (firstDiff > 0) {
                isIncrease = true;
            } else {
                isDecrease = true;
            }

            if (isIncrease == true) {
                isLineSafe = isValidIncrease(lineValues);
                while (isLineSafe == true && lineValues.size() > 1) {
                    isLineSafe = isValidIncrease(lineValues);
                    System.out.println(isLineSafe);
                }
            } else if (isDecrease == true) {
                isLineSafe = isValidDecrease(lineValues);
                while (isLineSafe == true && lineValues.size() > 1) {
                    isLineSafe = isValidDecrease(lineValues);
                    System.out.println(isLineSafe);
                }
            } else {
                return false;
            }
        }
        return isLineSafe;
    }
}
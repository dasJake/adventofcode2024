package main._02;

import java.io.File;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.IntStream;
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

            boolean isLineSafe = isLineFixable(lineValues);
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
        long diff = lineValues.get(1) - lineValues.get(0);
        System.out.println("incr :" + lineValues.get(0));
        System.out.println("incr+:" + lineValues.get(1));
        lineValues.remove(0);

        if (diff > 0 && diff < 4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidDecrease(List<Long> lineValues) {
        long diff = lineValues.get(1) - lineValues.get(0);
        System.out.println("decr :" + lineValues.get(0));
        System.out.println("decr+:" + lineValues.get(1));
        lineValues.remove(0);

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
                    System.out.println("safe :" + isLineSafe);
                }
            } else if (isDecrease == true) {
                isLineSafe = isValidDecrease(lineValues);
                while (isLineSafe == true && lineValues.size() > 1) {
                    isLineSafe = isValidDecrease(lineValues);
                    System.out.println("safe :" + isLineSafe);
                }
            } else {
                return false;
            }
        }
        return isLineSafe;
    }

    private static List<Boolean> errorDetector (List<Long> lineValues) {
        boolean isLineSafe = false;
        boolean isIncrease = false;
        boolean isValidIncrease = false;
        boolean isDecrease = false;
        boolean isValidDecrease = false;
        int outputIndex = 0;
        List<Boolean> output = new ArrayList<>();

        if (lineValues.size() > 1) {
            long firstDiff = lineValues.get(1) - lineValues.get(0);
            if (firstDiff == 0) {
                output.add(false);
            } else if (firstDiff > 0) {
                isIncrease = true;
            } else {
                isDecrease = true;
            }

            if (isIncrease == true) {
                output.add(isValidIncrease(lineValues));
                while (lineValues.size() > 1) {
                    output.add(isValidIncrease(lineValues));
                }
            } else if (isDecrease == true) {
                output.add(isValidDecrease(lineValues));
                while (lineValues.size() > 1) {
                    output.add(isValidDecrease(lineValues));
                }
            } else {
                output.add(false);
            }
        }
        return output;
    }

    private static boolean isLineFixable (List<Long> lineValues) {
        List<Long> lineValuesCopy = new ArrayList<>(lineValues);
        List<Long> lineValuesCopy2 = new ArrayList<>(lineValues);
        List<Long> lineValuesCopy3 = new ArrayList<>(lineValues);
        List<Long> lineValuesCopy4 = new ArrayList<>(lineValues);
        boolean pairIsSafe = true;
        List<Boolean> errorList = errorDetector(lineValuesCopy3);
        System.out.println(errorList);
        boolean lineIsSafe = isLineSafe(lineValues);
        System.out.println("fix lineIsSafe: " + lineIsSafe);

        if (!lineIsSafe) {

            int index;
            int indexOfError = 0;
            for (index = 0; index < lineValuesCopy.size() - 1 && pairIsSafe == true; index++) {

                List<Long> currentPair = new ArrayList<>(List
                    .of(lineValuesCopy.get(index), lineValuesCopy.get(index+1)));

                pairIsSafe = isLineSafe(currentPair);

                if (!pairIsSafe) {
                    indexOfError = index;
                    System.out.println("found error at: " + indexOfError);
                }
            }
            indexOfError = IntStream.range(0, errorList.size())
                .filter(i -> !errorList.get(i))
                .findFirst()
                .orElse(-1);
            System.out.println("error from list at index: " + indexOfError);

            if (indexOfError != -1) {
                System.out.println("try fixing with: " + lineValuesCopy.remove(indexOfError));
                lineIsSafe = isLineSafe(lineValuesCopy);
            }
            if (!lineIsSafe && indexOfError != -1) {
                System.out.println("try fixing with: " + lineValuesCopy2.remove(indexOfError + 1));
                lineIsSafe = isLineSafe(lineValuesCopy2);
            }
            if (!lineIsSafe && indexOfError > 0) {
                System.out.println("try fixing with: " + lineValuesCopy4.remove(indexOfError - 1));
                lineIsSafe = isLineSafe(lineValuesCopy4);
            }
        }
        return lineIsSafe;
    }
}
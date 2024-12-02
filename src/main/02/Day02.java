package main._02;

import java.io.File;

import java.util.Arrays;
import java.util.function.Consumer;

import util.FileConsumer;

public class Day02 {
    public static void main(String[] args) {
        System.out.println("advent day 02");

        ClassLoader classLoader = Day02.class.getClassLoader();
        File inputFile = new File(classLoader.getResource("src/main/resources/input/02/input.txt").getFile());

        Consumer<String> inputToLine = line -> {
            Long[] lineValues = Arrays.stream(line.trim().split("\\s+"))
                .map(Long::parseLong)
                .toArray(Long[]::new);
            System.out.println(Arrays.toString(lineValues));
        };

        FileConsumer fileConsumer = new FileConsumer();
        fileConsumer.consumeFile(inputFile, inputToLine);
    
    }
}
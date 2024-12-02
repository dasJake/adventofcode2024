package main._01;

import java.util.*;
import java.util.stream.*;
import java.io.*;


public class main {
    public static void main(String[] args) {
        System.out.println("advent day1");
        
        ClassLoader classloader = main.class.getClassLoader();
        File inputFile = new File(classloader.getResource("src/main/resources/input/01/input.txt").getFile());

        List<Long> leftlist = new ArrayList<>();
        List<Long> rightlist = new ArrayList<>();
                
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineValues = line.trim().split("\\s+");

                leftlist.add(Long.parseLong(lineValues[0]));
                rightlist.add(Long.parseLong(lineValues[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(leftlist);
        Collections.sort(rightlist);
        List<Long> diffList = IntStream.range(0, Math.min(leftlist.size(), rightlist.size()))
            .mapToObj(i -> Math.abs(leftlist.get(i) - rightlist.get(i)))
            .collect(Collectors.toList());
        
        long sumDiffList = diffList.stream().mapToInt(Long::intValue).sum();
        System.out.println(sumDiffList);

        Map<Long, Long> occurrences = leftlist.stream()
            .collect(Collectors.toMap(
                leftElement -> leftElement,
                leftElement -> rightlist.stream().filter(rightElement -> rightElement.equals(leftElement)).count()
            ));

        long sumOccurrencesTimesValues = occurrences.entrySet().stream()
            .mapToLong(entry -> entry.getKey() * entry.getValue())
            .sum();

        System.out.println(sumOccurrencesTimesValues);
    }
}
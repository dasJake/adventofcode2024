import java.util.*;
import java.util.stream.*;
import java.io.*;


public class main {
    public static void main(String[] args) {
        System.out.println("advent day1");
        
        ClassLoader classloader = main.class.getClassLoader();
        File inputFile = new File(classloader.getResource("resources/input/01/input.txt").getFile());

        List<Integer> leftlist = new ArrayList<>();
        List<Integer> rightlist = new ArrayList<>(); 
                
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineValues = line.trim().split("\\s+");

                leftlist.add(Integer.parseInt(lineValues[0]));
                rightlist.add(Integer.parseInt(lineValues[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(leftlist);
        Collections.sort(rightlist);
        List<Integer> diffList = IntStream.range(0, Math.min(leftlist.size(), rightlist.size()))
            .mapToObj(i -> Math.abs(leftlist.get(i) - rightlist.get(i)))
            .collect(Collectors.toList());
        
        int sumDiffList = diffList.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sumDiffList);
    }
}
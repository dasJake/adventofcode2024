package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.function.Consumer;

public class FileConsumer {
    public void consumeFile (String inputFilePath, Consumer<String> lineProcessor) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineProcessor.accept(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
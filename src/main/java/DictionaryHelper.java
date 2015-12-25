import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class DictionaryHelper {
    static String dicPath = "Dictionary.txt";

    public static HashSet<String> getDictionarySet() {
        HashSet<String> dictionary = new HashSet<>();
        Path dictionaryPath = Paths.get(dicPath);

        try (BufferedReader reader = Files.newBufferedReader
                (dictionaryPath, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
            return dictionary;
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
        return null;
    }
}
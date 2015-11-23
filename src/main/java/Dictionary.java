import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    static String dicPath = "Dictionary.txt";
    static String editedDicPath = "EditedDictionary.txt";

    private static ArrayList<String> removeDuplicates(ArrayList<String> al) {
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);
        return al;
    }

    protected static ArrayList<String> setDictionary() {
        Path dictionaryPath = Paths.get(dicPath);
        Path editedDictionaryPath = Paths.get(editedDicPath);

        ArrayList<String> dictionary = new ArrayList<>();
        ArrayList<String> editedDictionary = new ArrayList<>();

        Charset charset = Charset.forName("US-ASCII");
        String line;

        try (BufferedReader reader = Files.newBufferedReader(dictionaryPath, charset);
             BufferedWriter writer = Files.newBufferedWriter(editedDictionaryPath, charset)) {
            // Reading dictionary's words from given txt
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
            // Removing duplicates
            editedDictionary = removeDuplicates(dictionary);
            // Creating EditedVocabulary.txt
            for (String object : editedDictionary) {
                writer.write(object);
                writer.newLine();
            }
        } catch (InvalidPathException e) {
            System.out.println("Invalid path of the dictionary file!");
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return editedDictionary;
    }
}
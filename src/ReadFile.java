import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.io.IOException;

public class ReadFile {
    String path;
    static String temp = "";

    ReadFile(String path) {
        this.path = path;
    }

    ReadFile() {
    }

    List<String> readFileContents() {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Вероятно, файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    public String[] splitLines(String lineOfFile) {
        String[] lineContents = lineOfFile.split(",");
        return lineContents;
    }

    public ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> listOfFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("csv")) {
                        listOfFiles.add(fileEntry.getName());
                    }
            }
        }
        return listOfFiles;
    }
}

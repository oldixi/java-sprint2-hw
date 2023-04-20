import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class ReadFile {
    String path;

    ReadFile(String path) {
        this.path = path;
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

}

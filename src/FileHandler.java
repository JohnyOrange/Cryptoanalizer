import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHandler {
    private FileHandler() {
    }

    public static Path createNewFile(Path path, String command, StringBuilder sb){
        String fileName = String.valueOf(path.getFileName());
        String fileExtension = getFileExtension(path);
        String newFileName = fileName.substring(0, fileName.indexOf('.')) + "(" + command + "d)" + fileExtension;
        String outputFIlePath = path.getParent() + "\\" + newFileName;

        if (!Files.exists(Path.of(outputFIlePath))){
            try {
                Files.createFile(Path.of(outputFIlePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.writeString(Path.of(outputFIlePath), sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Path.of(outputFIlePath);
    }

    public static String getFileExtension(Path path){
        String fileName = String.valueOf(path.getFileName());
        return fileName.substring(fileName.indexOf('.'));
    }

    public static List<String> readFile(Path path) {
        List<String> list;
        try {
            list = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

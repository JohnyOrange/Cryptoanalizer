import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHandler {

    public static void createNewFile(Path path, String command, StringBuilder sb){
        String fileName = String.valueOf(path.getFileName());
        String fileExtension = fileName.substring(fileName.indexOf('.'));
        String newFileName = fileName.substring(0, fileName.indexOf('.')) + "(" + command + "ed)" + fileExtension;
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
    }

    public static Path createNewFile(Path path, String command){
        String fileName = String.valueOf(path.getFileName());
        String fileExtension = fileName.substring(fileName.indexOf('.'));
        String newFileName = fileName.substring(0, fileName.indexOf('.')) + "(" + command + "ed)" + fileExtension;
        String outputFIlePath = path.getParent() + "\\" + newFileName;

        if (!Files.exists(Path.of(outputFIlePath))){
            try {
                Files.createFile(Path.of(outputFIlePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Path.of(outputFIlePath);
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

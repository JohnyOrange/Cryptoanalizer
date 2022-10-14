import java.nio.file.Files;
import java.nio.file.Path;

public class ErrorHandler {
    private static final String EXTENSION = ".txt";
    private ErrorHandler() {
    }

    public static void checkNumberOfArguments(String[] args){
        if (args.length != 3){
            throw new RuntimeException("The program takes 3 arguments: \n<command>, " +
                    "<path to the processed file>, <encryption key or path to the reference file>");
        }
    }
    public static String getCommand(String command){
        if (Main.DECODE.equals(command) || Main.ENCODE.equals(command) || Main.BRUTEFORCE.equals(command)){
            return command;
        } else {
            throw new RuntimeException("""
                    Illlegal command!

                    Command:\s
                    encode - encode txt file from path with given key
                    decode - decode txt file from path with given key
                    bruteforce - decode txt file from path with sample txt file from 3rd argument file path""");
        }
    }
    public static Path getPath(String pathOfFile){
        Path path = Path.of(pathOfFile);
        String fileExtension = FileHandler.getFileExtension(path);
        if (!EXTENSION.equals(fileExtension)){
            throw new RuntimeException("Program works with txt files only");
        } else if (Files.isDirectory(path)) {
            throw new RuntimeException("Your path is directory");
        } else if (Files.isRegularFile(path)) {
            return path;
        } else {
            throw new RuntimeException("Check your path");
        }
    }
}

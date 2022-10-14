import java.nio.file.Path;

public class Main {

    public static final String ENCODE = "encode";
    public static final String DECODE = "decode";
    public static final String BRUTEFORCE = "bruteforce";
    public static void main(String[] args) {

        ErrorHandler.checkNumberOfArguments(args);

        String command = ErrorHandler.getCommand(args[0]);

        Path originalFilePath = ErrorHandler.getPath(args[1]);

        if (ENCODE.equals(command)){
            int key = Integer.parseInt(args[2]);
            StringBuilder sb = Encoder.encode(originalFilePath, key);
            FileHandler.createNewFile(originalFilePath, command, sb);
        } else if (DECODE.equals(command)){
            int key = Integer.parseInt(args[2]);
            StringBuilder sb = Encoder.decode(originalFilePath, key);
            FileHandler.createNewFile(originalFilePath, command, sb);
        } else if (BRUTEFORCE.equals(command)){
            Path sampleFilePath = ErrorHandler.getPath(args[2]);
            int key = StaticAnalyzer.decode(sampleFilePath, originalFilePath, command);
            System.out.println("Encoding key was: " + key);
        }

    }
}

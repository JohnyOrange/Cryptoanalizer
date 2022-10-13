import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        String command = args[0];
        if (command.equals("encrypt")){
            Path path = Path.of(args[1]);
            int key = Integer.parseInt(args[2]);
            StringBuilder sb = Encryptor.encrypt(path, key);
            FileHandler.createNewFile(path, command, sb);

        }
        if (command.equals("decrypt")){
            Path path = Path.of(args[1]);
            int key = Integer.parseInt(args[2]);
            StringBuilder sb = Encryptor.decrypt(path, key);
            FileHandler.createNewFile(path, command, sb);
        }
        if (command.equals("analyze")){
            Path samplePath = Path.of(args[1]);
            Path encryptedPath = Path.of(args[2]);
            StaticAnalyzer.decrypt(samplePath, encryptedPath);
        }

    }
}

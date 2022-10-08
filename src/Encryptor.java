
import java.nio.file.Path;
import java.util.List;

public class Encryptor {

    public static StringBuilder encrypt(Path path, int key){

        List<String> list = FileHandler.readFile(path);

        StringBuilder sb = new StringBuilder();
        for (String line :
                list) {
            char[] currentString = line.toCharArray();
            for (char letter : currentString) {
                char encryptedLetter;
                char[] range;

                if (Character.isAlphabetic(letter)) {
                    range = Alphabet.getRange(letter);
                } else {
                    sb.append(letter);
                    continue;
                }

                encryptedLetter = (char) (letter + key);
                assert range != null;
                if (encryptedLetter > range[1]) {
                    encryptedLetter = (char) (range[0] + (encryptedLetter - range[1]) - 1);
                }
                if (encryptedLetter < range[0]) {
                    encryptedLetter = (char) (range[1] - (range[0] - encryptedLetter) + 1);
                }
                sb.append(encryptedLetter);
            }
            sb.append('\n');
        }
        return sb;
    }
    public static StringBuilder decrypt(Path path, int key){
        int decryptKey = key * -1;
        return encrypt(path, decryptKey);
    }



}

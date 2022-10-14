
import java.nio.file.Path;
import java.util.List;

public class Encoder {
    private Encoder() {
    }

    public static StringBuilder encode(Path path, int key){

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

                int alphabetLength = Alphabet.getAlphabetLength(range);

                if (Math.abs(key) > alphabetLength){
                    key = key % (alphabetLength);
                }

                encryptedLetter = (char) (letter + key);

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
    public static StringBuilder decode(Path path, int key){
        return encode(path, -key);
    }



}

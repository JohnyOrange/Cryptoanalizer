import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StaticAnalyzer {

    private StaticAnalyzer() {
    }

    public static int decode(Path sampleFilePath, Path encodedFilePath, String command){

        boolean comparator = false;
        int key = 0;
        StringBuilder sb = new StringBuilder();
        Path decodedFilePath = FileHandler.createNewFile(encodedFilePath, command, sb);

        while (!comparator && key < 33){
            List<String> sampleFileList = FileHandler.readFile(sampleFilePath);
            int[] sampleFileGraph = getFileGraph(sampleFileList);
            List<String> encryptedFileList = FileHandler.readFile(encodedFilePath);
            int[] encryptedFileGraph = getFileGraph(encryptedFileList);

            comparator = compare(sampleFileGraph, encryptedFileGraph);

            if (comparator){
                break;
            } else {
                sb = Encoder.decode(encodedFilePath, 1);
                encodedFilePath = decodedFilePath;
                try {
                    Files.writeString(encodedFilePath, sb);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                key++;
            }
        }
        return key;



    }

    private static boolean compare(int[] sampleFileGraph, int[] encryptedFileGraph) {
        boolean comparator = false;
        for (int i = 0; i < sampleFileGraph.length; i++) {
            if (Math.abs(sampleFileGraph[i] - encryptedFileGraph[i]) < 10){
                comparator = true;
            } else {
                comparator = false;
                break;
            }
        }
        return comparator;
    }

    private static int[] getFileGraph(List<String> sampleFileList) {
        int length = Alphabet.getCombineAlphabetLengtn();
        int[] fileGraph = new int[length];
        int count = 0;
        for (String line :
                sampleFileList) {
            for (int i = 0; i < line.length(); i++) {
                char letter = line.charAt(i);
                if (Character.isAlphabetic(letter)){
                    int index = Alphabet.getIndex(letter);
                    fileGraph[index]++;
                    count++;
                }
            }
        }
        normalize(fileGraph, count);
        return fileGraph;
    }

    private static void normalize(int[] fileGraph, int count) {
        double coefficient = (double) count/1000;
        for (int i = 0; i < fileGraph.length; i++) {
            fileGraph[i] = (int)Math.round(fileGraph[i] / coefficient);
        }
    }
}

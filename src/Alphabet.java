public class Alphabet {
    private static final char EN_SMALL_BEGINING = 'a';
    private static final char EN_SMALL_ENDING = 'z';
    private static final char EN_BIG_BEGINING = 'A';
    private static final char EN_BIG_ENDING = 'Z';
    private static final char RU_SMALL_BEGINIG = 'а';
    private static final char RU_SMALL_ENDING = 'я';
    private static final char RU_BIG_BEGINING ='А';
    private static final char RU_BIG_ENDING = 'Я';

    public static char[] getRange (char letter){
        char first;
        char last;
        if (EN_SMALL_BEGINING <= letter && letter <= EN_SMALL_ENDING){
            first = EN_SMALL_BEGINING;
            last = EN_SMALL_ENDING;
        } else if (EN_BIG_BEGINING <= letter && letter <= EN_BIG_ENDING) {
            first = EN_BIG_BEGINING;
            last = EN_BIG_ENDING;
        } else if (RU_SMALL_BEGINIG <= letter && letter <= RU_SMALL_ENDING) {
            first = RU_SMALL_BEGINIG;
            last = RU_SMALL_ENDING;
        } else if (RU_BIG_BEGINING <= letter && letter <= RU_BIG_ENDING) {
            first = RU_BIG_BEGINING;
            last = RU_BIG_ENDING;
        } else {
            return null;
        }
        return new char[]{first, last};
    }

    public static int getAlphabetLengtn(){
        return (EN_SMALL_ENDING - EN_SMALL_BEGINING) + 1
                + (EN_BIG_ENDING - EN_BIG_BEGINING) + 1
                + (RU_SMALL_ENDING - RU_SMALL_BEGINIG) + 1
                + (RU_BIG_ENDING - RU_BIG_BEGINING) + 1;
    }


    public static int getIndex(char letter) {
        int index = -1;
        if (EN_SMALL_BEGINING <= letter && letter <= EN_SMALL_ENDING){
            index = letter - EN_SMALL_BEGINING;
        } else if (EN_BIG_BEGINING <= letter && letter <= EN_BIG_ENDING) {
            index = letter - EN_BIG_BEGINING + (EN_SMALL_ENDING - EN_SMALL_BEGINING);
        } else if (RU_SMALL_BEGINIG <= letter && letter <= RU_SMALL_ENDING) {
            index = letter - RU_SMALL_BEGINIG + (EN_SMALL_ENDING - EN_SMALL_BEGINING) + (EN_BIG_ENDING - EN_BIG_BEGINING);
        } else if (RU_BIG_BEGINING <= letter && letter <= RU_BIG_ENDING) {
            index = letter - RU_BIG_BEGINING + (EN_SMALL_ENDING - EN_SMALL_BEGINING) + (EN_BIG_ENDING - EN_BIG_BEGINING) + (RU_SMALL_ENDING - RU_SMALL_BEGINIG);
        }
        return index;
    }
}

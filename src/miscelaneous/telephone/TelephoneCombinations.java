package miscelaneous.telephone;

public class TelephoneCombinations {
    public static final String[] numberToKeys = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static void runCombinations(String numbers) {
        StringBuffer combination = new StringBuffer();
        runCombinationsRec(numbers, combination, 0);
    }

    private static void runCombinationsRec(String numbers, StringBuffer combination, int currIndex) {
        if (currIndex == numbers.length()) {
            printCombination(combination);
        } else {
            int numberToKeyLength = numberToKeys[currIndex].length();

            for (int i = 0; i < numberToKeyLength; i++) {
                Character c = numberToKeys[currIndex].charAt(i);

                combination.append(c);
                runCombinationsRec(numbers, combination, currIndex + 1);
                combination.deleteCharAt(currIndex);
            }
        }
    }

    private static void printCombination(StringBuffer combination) {
        System.out.println(combination);
    }
}

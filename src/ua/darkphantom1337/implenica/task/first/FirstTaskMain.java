package ua.darkphantom1337.implenica.task.first;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstTaskMain {

    private static String prefix = "[Task-1] -> ";

    /**
     * Total correct sequences
     */
    private static List<String> correctBracketSequences = new ArrayList<>();

    public static void main(String[] args) {
        /**
         * Writing opening brackets and and checking for input errors
         */
        System.out.print(prefix + "Please write the number of opening brackets: ");
        String openingBrackets = new Scanner(System.in).nextLine();
        int opBrackets = 0, endBrackets = 0;
        while (true) {
            try {
                opBrackets = Integer.parseInt(openingBrackets);
                break;
            } catch (NumberFormatException e) {
                System.out.print(prefix + " [ERROR] Please write the number of opening brackets: ");
                openingBrackets = new Scanner(System.in).nextLine();
            }
        }
        /**
         * Writing closing brackets and and checking for input errors
         */
        System.out.print(prefix + "Please write the number of closing brackets: ");
        String closingBrackets = new Scanner(System.in).nextLine();
        while (true) {
            try {
                endBrackets = Integer.parseInt(closingBrackets);
                break;
            } catch (NumberFormatException e) {
                System.out.print(prefix + " [ERROR] Please write the number of closing brackets: ");
                closingBrackets = new Scanner(System.in).nextLine();
            }
        }
        /**
         * if opening brackets == closingBrackets, start creating sequences using the createBracketSequence algorithm
         */
        if (opBrackets == endBrackets) {
            createBracketSequence(0, 0, opBrackets + endBrackets, new ArrayList<Character>());
            /**
             * Print all correct bracket sequences and total size
             */
            System.out.println(prefix + "Correct sequence for " + opBrackets + " '(' and " + endBrackets + " ')' bracket:");
            correctBracketSequences.forEach(System.out::println);
            System.out.println(prefix + "Correct bracket sequences total: " + correctBracketSequences.size());
        } else {
            System.out.println(prefix + " [ERROR] It is not possible to construct a correct parenthesis sequence because the number of opening and closing parentheses is not the same.\n\n\n\n\n");
            main(new String[]{});
        }
    }

    public static void createBracketSequence(int cnt, int ind, int k, List<Character> sequences) {
        /**
         * If there is enough space for an open parenthesis, then add it to the general sequence
         */
        if (cnt <= k - ind - 2) {
            sequences.add(ind, '(');
            createBracketSequence(cnt + 1, ind + 1, k, sequences);
        }
        /**
         * If the difference between the closing and opening brackets is greater than 0, then we
         * add a closed bracket to the general sequence.
         */
        if (cnt > 0) {
            sequences.add(ind, ')');
            createBracketSequence(cnt - 1, ind + 1, k, sequences);
        }
        /**
         * if the number of brackets is equal to the total number of brackets, then this
         * is a ready-made sequence
         */
        if (ind == k && cnt == 0) {
            String summary = "";
            /**
             * We collect a string from the list of characters, cut it to the total number
             * of brackets and check it for correctness using the
             * checkIsCorrectBracketsSequence algorithm
             */
            for (Character ch : sequences) summary += ch.toString();
            summary = summary.length() <= 6 ? summary : summary.subSequence(0, k).toString();
            /**
             * check it for correctness using the checkIsCorrectBracketsSequence algorithm
             * check that the sequence does not repeat
             */
            if (checkIsCorrectBracketsSequence(summary) && !correctBracketSequences.contains(summary))
            /**
             * Add correct sequence to total list.
             */
                correctBracketSequences.add(summary);
        }

    }

    public static Boolean checkIsCorrectBracketsSequence(String sequence) {
        int k = 0;
        for (char s : sequence.toCharArray()) {
            /**
             * if the parenthesis opens, add 1 otherwise subtract 1
             */
            k += s == ')' ? -1 : 1;
            /**
             * if the k is less than 0 the sequence is not correct
             */
            if (k < 0) return false;
        }
        return k == 0;
    }
}

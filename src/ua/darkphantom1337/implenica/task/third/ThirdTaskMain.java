package ua.darkphantom1337.implenica.task.third;

import java.math.BigInteger;

public class ThirdTaskMain {

    public static void main(String[] args) {
        System.out.println(getSum(100));
    }

    /**
     * Method calculate factorial of input number
     * and calculate the sum of all factorial number's digits.
     *
     * @param num : integer number
     * @return sum of all factorial number's digits
     */

    public static int getSum(int num) {
        /**
         *  Factorial may be is large to handled by long, int or any other primitive data type in java.
         *  So should use BigInteger library.
         */
        BigInteger fact = BigInteger.ONE;
        int sum = 0;
        // Calculate the factorial of number
        for (int i = 2; i <= num; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
        String number = fact + "";
        //Calculate sum of all digits in the number
        for (int i = 0; i < number.length(); i++)
            sum += Integer.parseInt(number.charAt(i) + ""); // cast all string's symbols to integer
        return sum;
    }

}

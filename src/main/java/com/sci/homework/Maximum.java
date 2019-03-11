/*
 Calculates the maximum out of three numbers read from the keyboard.
 Method getMax() has three integer (int) parameters, that returns maximal of the three numbers.

 @author Gheo Coanta

 */

package com.sci.homework;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Maximum {

    /**
     * @param numerOne
     * @param numberTwo
     * @return maximum between three numbers
     * @throws InputMismatchException
     */
    //getMax function: returns the maximum from 2 numbers;
    private static int getMax(int numerOne, int numberTwo) throws InputMismatchException {
        if (numerOne >= numberTwo) {
            return numerOne;
        } else {
            return numberTwo;
        }
    }

    private static int getMax(int numberOne, int numberTwo, int numberThree) throws InputMismatchException {
        return getMax(getMax(numberOne, numberTwo), numberThree);
    }

    public static void main(String[] args) {


        boolean exceptionFound=true;

        do {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Please input the first number: ");
                int numberOne = input.nextInt();
                System.out.print("Please input the second number: ");
                int numberTwo = input.nextInt();
                System.out.print("Please input the third number: ");
                int numberThree = input.nextInt();

                System.out.print("The maxim between " + numberOne + " ," + numberTwo + " and " + numberThree + " is: "
                        + getMax(numberOne, numberTwo, numberThree));

                exceptionFound=false;

            } catch (InputMismatchException e) {
                System.out.println("The numbers picked should be integers! ");
            }
        } while (exceptionFound);

    }
}

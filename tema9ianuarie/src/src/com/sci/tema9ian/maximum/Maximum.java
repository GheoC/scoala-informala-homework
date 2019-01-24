/*
 Calculates the maximum out of two number read from the keyboard.
 Method getMax() has two integer (int) parameters, that returns maximal of the two numbers.

 @author Gheo Coanta

 */

package src.com.sci.tema9ian.maximum;

import java.util.Scanner;

public class Maximum {


    //getMax function: returns the maximum from 2 numbers;
    public static int getMax(int numerOne, int numberTwo) {

        if (numerOne >= numberTwo) {
            return numerOne;
        } else {
            return numberTwo;
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("introduceti un numar: ");
        int numar1 = input.nextInt();
        System.out.print("introduceti un numar: ");
        int numar2 = input.nextInt();


        System.out.print("Maximul dintre " + numar1 + " si " + numar2 + " este: " + getMax(numar1, numar2));

    }
}

/*
Se introduce de la tastatura ora si minutul.
Aplicatia valideaza daca ora este valida si o afiseaza.
Daca ora nu este valida, se afiseaza "Incorrect time".

Validarea se efectueaza prin functia validateTime()

Pentru o afisare corecta se convertesc variabilele "ora" si "minute" in String; daca fie ora sau minutul
sunt cifre la afisaj se mai adauga un "0" in fata.

@author Gheo Coanta
 */


package src.com.sci.tema9ian.time;

import java.util.Scanner;

public class Clock {

    public static boolean validateTime(int hh, int mm) {
        if ((hh <= 24 & hh >= 0) & (mm >= 0 & mm <= 60)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("What time is it?  ");
        int ora = input.nextInt();
        int minute = input.nextInt();

        if (validateTime(ora, minute)) {
            String oraString;
            String minuteString;
            if (ora < 10) {
                oraString = "0" + Integer.toString(ora);
            } else {
                oraString = Integer.toString(ora);
            }
            if (minute < 10) {
                minuteString = "0" + Integer.toString(minute);
            } else {
                minuteString = Integer.toString(minute);
            }

            System.out.println("The time is " + oraString + ":" + minuteString + " now.");

        } else {
            System.out.println("Incorrect time!");
        }

    }
}

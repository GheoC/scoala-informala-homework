/*
Se introduce de la tastatura ora si minutul.
Aplicatia valideaza daca ora este valida si o afiseaza.
Daca ora nu este valida, se afiseaza "Incorrect time".

Validarea se efectueaza prin functia validateTime()

Pentru o afisare corecta se convertesc variabilele "ora" si "minute" in String; daca fie ora sau minutul
sunt cifre la afisaj se mai adauga un "0" in fata.

@author Gheo Coanta
 */


package com.sci.homework;

import java.util.Scanner;

public class Clock {

    public static void validateTime(int hh, int mm) throws InvalidInputException {

        boolean hourValidate = (hh >= 24 || hh < 0);
        boolean minuteValidate = (mm < 0 || mm >= 60);

        if (hourValidate && minuteValidate) {
            throw new InvalidInputException("The hour and minutes are not correctly input");
        }
        if (hourValidate) {
            throw new InvalidInputException("The hour is not correctly input!");
        }
        if (minuteValidate) {
            throw new InvalidInputException("The minutes are not correctly input!");
        }
    }

    public static void main(String[] args) {

        boolean exceptionFound = true;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("What time is it?  ");
            int ora = input.nextInt();
            int minute = input.nextInt();

            try {
                validateTime(ora, minute);
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

                exceptionFound = false;

            } catch (InvalidInputException e) {
                System.out.println("Not a correct time! -> " + e.getMessage());
            }
        } while (exceptionFound);

    }
}


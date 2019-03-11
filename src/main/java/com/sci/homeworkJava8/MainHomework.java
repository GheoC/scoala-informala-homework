package com.sci.homeworkJava8;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainHomework {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
//        System.out.println("Please input the name of the source file: ");
//        String fileSource = input.nextLine();
//        System.out.println("Please input the name of the file: ");
//        String fileOutput = input.nextLine();
//        System.out.println("Please enter the month for your selection. Mut be a number from 1 to 12: ");
//        int month = input.nextInt();

        Operations operations = new Operations();
//
        try {
            System.out.println("The output file contains the following persons: \n");

            operations.doSelection("Persons", 11, "Out");

            System.out.println("==========================================================\n");

            System.out.println("The EZ output file contains the following persons: \n");

            operations.searchBirthdayEZ("Persons", 6, " Out-EZ-Way");

            System.out.println("==========================================================\n");

        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File not found!..." + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Not a correct birthday somewhere in file!  " + e.getMessage());
        }



    }

}

package com.sci.homeworkJava8;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Operations loads from a file a structure like first name, last name, date of birth and writes back another
 * file containing only first name, last name ordered alphabetically for all the of all matches which have the
 * birthday on a month indicated.
 * The personsList stores Persons from the source file
 * the resultList stores Persons that are selected by the month of birth criteria.
 */
public class Operations {

    private List<Person> personsList = new ArrayList<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private List<Person> resultList = new ArrayList<>();


    /**
     * Reads from the source file and stores the information in a list of Persons. The information from each line of
     * the file are name, surname and birthday.
     *
     * @param file name of the source file that user input. This is the source file for name, surname and birthday of
     *             each person
     */
    public void readFileFrom(String file) throws IOException, DateTimeParseException {
        Charset charset = Charset.forName("UTF8");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("files/" + file + ".csv"), charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] linesSplit = line.split(", ");
                Person person = new Person(linesSplit[0], linesSplit[1], LocalDate.parse(linesSplit[2], formatter));
                this.personsList.add(person);
            }
        }
    }

    /**
     * Searches in the list of persons for the persons born in the month provided by the user.
     *
     * @param month The month entered by the use for the selection from the list of Persons
     * @throws InvalidInputException Custom excetion used for the variable month. If the month picked by the user is
     *                               greater than 12 or less than 0 than the excetion is thrown.
     */
    public void searchBirthday(int month) throws InvalidInputException {

        String m1 = "";

        if (month < 1 || month > 12) {
            throw new InvalidInputException("The month must be between 1 and 12!");
        }

        this.resultList = personsList.stream()
                .filter(person -> person.getBirthday().getMonthValue() == month)
                .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getSurName))
                .collect(Collectors.toList());

        for (Person person : resultList) {
            System.out.println(person.getFirstName() + ", " + person.getSurName());
        }

        if (resultList.isEmpty()) {
            System.out.println("No persons were found with birth month " + month + "(" + Month.of(month) + ")");
        }
    }

    /**
     * Method that writes the output selection into a file. The name of the file is provided by the user.
     *
     * @param file the output file provided by the user;
     * @throws IOException exception.
     */
    private void writeFileTo(String file) throws IOException {

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("files/" + file + ".csv"))) {

            for (Person person : this.resultList) {
                String line = person.getFirstName() + ", " + person.getSurName();
                bfw.write(line);
                bfw.newLine();
            }
        }
    }

    /**
     * Method that calls the methods readFileFrom(), searchBirthday() and writeFileTo()
     *
     * @param fileInput  source file provided by the user
     * @param month      The month entered by the use for the selection from the list of Persons
     * @param fileOutput the output file provided by the user;
     * @throws IOException exception
     */
    void doSelection(String fileInput, int month, String fileOutput) throws IOException {
        readFileFrom(fileInput);
        searchBirthday(month);
        writeFileTo(fileOutput);
    }

    public List<Person> getResultList() {
        return resultList;
    }


    /**
     * (After hint from George), a new way to search for Persons with birth month provided by user
     * The method reads the file into a stream and wrtite the poutput file with the Persons born in the month provided
     * by the user
     *
     * @param fileInput  the source file with Persons
     * @param month      the birth month you are looking for
     * @param fileOutput the output file with the persons selected
     * @throws IOException exception
     */
    public void searchBirthdayEZ(String fileInput, int month, String fileOutput) throws IOException {


        if (month < 1 || month > 12) {
            throw new InvalidInputException("The month must be between 1 and 12!");
        }
        try (Stream<String> streamReader = Files.lines(Paths.get("files/" + fileInput + ".csv"));

             PrintWriter printWriter = new PrintWriter("files/" + fileOutput + ".csv")) {
            streamReader
                    .map(lines -> lines.split(", "))
                    .map(linesSplit -> new Person(linesSplit[0], linesSplit[1], LocalDate.parse(linesSplit[2],
                            formatter)))
                    .filter(person -> person.getBirthday().getMonthValue() == month)
                    .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getSurName))
                    .map(Person::toString)
                    .forEach(line->{
                        System.out.println(line);
                        printWriter.println(line);
                    });
                    //.forEach(printWriter::println);
        }
    }
}



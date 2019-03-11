package com.sci.homeworkJava8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationsTest {


    /**
     * test for method searchBirthday when month in incorrectly provided
     */
    @Test
    void invalidMonth() {

        Operations operations = new Operations();
        assertThrows(InvalidInputException.class, () -> {
            operations.searchBirthday(14);
        });
    }

    /**
     * Testing if the created list by method searchBirthday(5) is the same as the expected result;
     *
     * @throws ParseException
     */
    @Test
    void doBirthdaySearchSuccess() throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        Operations operations = new Operations();

        operations.readFileFrom("PersonsTest");
        operations.searchBirthday(5);

        //getting the list running method searchBirthday
        List<Person> personList = operations.getResultList();

        //creating the expected result List.
        List<Person> resultExpected = new ArrayList<>();
        resultExpected.add(new Person("Alin", "Adam"));
        resultExpected.add(new Person("Alin", "B"));
        resultExpected.add(new Person("Alin", "G"));
        resultExpected.add(new Person("Bogdan", "A"));
        resultExpected.add(new Person("Bogdan", "C"));


        //verifying if the two lists are the same

        boolean equal = true;
        for (int i = 0; i < personList.size(); i++) {
            if (!(
                    (personList.get(i).getFirstName().equals(resultExpected.get(i).getFirstName())) &&
                            (personList.get(i).getSurName().equals(resultExpected.get(i).getSurName())))) {
                equal = false;
            }
        }

        //we are expecting the result should be true
        assertTrue(equal);
    }

    /**
     * test for reading from a not existing file
     *
     */
    @Test
    void readFromNotExistingFile() {
        Operations operations = new Operations();
        assertThrows(IOException.class,()->operations.readFileFrom("Persons1"));
    }
}

package com.sci.homework;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClockTest {

    /**
     * test method validateTime()
     * test for an invalid hour
     */
    @org.junit.jupiter.api.Test
    void invalidHour() {

        //invalid hour
        int hh = 24;
        int mm = 45;

        assertThrows(InvalidInputException.class, () -> {
            Clock.validateTime(hh, mm);
        });
    }

    /**
     * test method validateTime()
     * test for an invalid minute
     */
    @org.junit.jupiter.api.Test
    void invalidMinute() {

        //invalid minute
        int hh = 22;
        int mm = 89;

        assertThrows(InvalidInputException.class, () -> {
            Clock.validateTime(hh, mm);
        });
    }

    /**
     * test method validateTime()
     * test for an invalid hour and invalid minute
     */
    @org.junit.jupiter.api.Test
    void invalidHourAndMinute() {

        //invalid minute
        int hh = -2;
        int mm = -43;

        assertThrows(InvalidInputException.class, () -> {
            Clock.validateTime(hh, mm);
        });

    }

}
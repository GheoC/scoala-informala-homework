package com.sci.homework;

/**Custom exception.
 *
 * I've found this method of handling exceptions on Youtube -> https://www.youtube.com/watch?v=W-N2ltgU-X4
 * minute 20:30.
 */
class InvalidInputException extends RuntimeException {

    InvalidInputException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

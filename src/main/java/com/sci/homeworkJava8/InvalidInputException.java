package com.sci.homeworkJava8;

/**Custom exception.
 *
 *
 */
class InvalidInputException extends RuntimeException {

    InvalidInputException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

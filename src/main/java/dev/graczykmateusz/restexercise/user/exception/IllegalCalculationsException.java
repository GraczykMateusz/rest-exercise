package dev.graczykmateusz.restexercise.user.exception;

public class IllegalCalculationsException extends RuntimeException {
    public IllegalCalculationsException() {
        super("Cannot divide by zero!");
    }
}

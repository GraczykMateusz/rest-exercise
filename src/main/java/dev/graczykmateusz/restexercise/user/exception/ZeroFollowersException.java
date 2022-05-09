package dev.graczykmateusz.restexercise.user.exception;

public class ZeroFollowersException extends RuntimeException {
    public ZeroFollowersException(String message) {
        super(message);
    }
}

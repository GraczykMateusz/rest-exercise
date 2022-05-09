package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.IllegalCalculationsException;
import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ExceptionAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleNotFoundException(Exception e) {
        return getErrorMessage(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalCalculationsException.class)
     ResponseEntity<String> handleBadRequestException(Exception e) {
        return getErrorMessage(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> getErrorMessage(Exception e, HttpStatus httpStatus) {
        String error = e.toString();
        LOG.error(error);
        return ResponseEntity.status(httpStatus).body(e.getMessage());
    }
}

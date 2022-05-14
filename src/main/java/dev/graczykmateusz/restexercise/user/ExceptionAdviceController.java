package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.GithubClientException;
import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import dev.graczykmateusz.restexercise.user.exception.ZeroFollowersException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ExceptionAdviceController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdviceController.class);

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleNotFoundException(UserNotFoundException e) {
        return getErrorMessage(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ZeroFollowersException.class)
    ResponseEntity<String> handleUnprocessableEntityException(ZeroFollowersException e) {
        return getErrorMessage(e, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(GithubClientException.class)
    ResponseEntity<String> handleGithubClientExceptionException(GithubClientException e) {
        return getErrorMessage(e, e.getHttpStatus());
    }

    private ResponseEntity<String> getErrorMessage(Exception e, HttpStatus httpStatus) {
        String error = e.toString();
        LOG.error(error);
        return ResponseEntity.status(httpStatus).body(e.getMessage());
    }
}

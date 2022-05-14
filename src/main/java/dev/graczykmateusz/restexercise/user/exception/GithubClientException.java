package dev.graczykmateusz.restexercise.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
public class GithubClientException extends RuntimeException {

    private final HttpStatus httpStatus;

    public GithubClientException(HttpClientErrorException e) {
        super(e);
        this.httpStatus = e.getStatusCode();
    }
}

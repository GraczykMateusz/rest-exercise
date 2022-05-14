package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.GithubClientException;
import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import dev.graczykmateusz.restexercise.user.exception.ZeroFollowersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExceptionAdviceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserController userController;

    @BeforeEach
    public void setUp() {
        ExceptionAdviceController underTest = new ExceptionAdviceController();

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(underTest)
                .build();
    }

    @Test
    void shouldReturnNotFoundException() throws Exception {
        when(userController.getUserData(any()))
                .thenThrow(new UserNotFoundException("UnknownUser"));

        mockMvc.perform(get("/api/v1/users/GraczykMateusz"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User with login \"UnknownUser\" not found!"));
    }

    @Test
    void shouldReturnUnprocessableEntityException() throws Exception {
        when(userController.getUserData(any()))
                .thenThrow(new ZeroFollowersException(
                        "Calculation cannot be performed because" +
                                " the number of followers is zero!"));

        mockMvc.perform(get("/api/v1/users/GraczykMateusz"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(
                        "Calculation cannot be performed because" +
                                " the number of followers is zero!"));
    }

    @Test
    void shouldReturnGithubClientException() throws Exception {
        when(userController.getUserData(any()))
                .thenThrow(new GithubClientException(HttpClientErrorException.create(
                        HttpStatus.BAD_REQUEST,
                        "",
                        HttpHeaders.EMPTY,
                        null,
                        null
                )));

        mockMvc.perform(get("/api/v1/users/GraczykMateusz"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        "org.springframework.web.client.HttpClientErrorException$BadRequest:" +
                                " 400 Bad Request"));
    }
}
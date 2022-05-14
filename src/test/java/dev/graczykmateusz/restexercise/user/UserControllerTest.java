package dev.graczykmateusz.restexercise.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController underTest;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                .build();
    }

    @Test
    void shouldReturnStatusOkAndUserDataBodyWithCreatedAtAsSeconds() throws Exception {
        when(userService.getUserData(any(String.class)))
                .thenReturn(new UserData(
                        43554417L,
                        "GraczykMateusz",
                        "Mateusz Graczyk",
                        "User",
                        "https://avatars.githubusercontent.com/u/43554417?v=4",
                        Instant.parse("2018-09-24T20:41:09Z"),
                        38.0)
                );

        mockMvc.perform(get("/api/v1/users/GraczykMateusz"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(
                        "{\"id\":43554417," +
                                "\"login\":\"GraczykMateusz\"," +
                                "\"name\":\"Mateusz Graczyk\"," +
                                "\"type\":\"User\"," +
                                "\"avatarUrl\":\"https://avatars.githubusercontent.com/u/43554417?v=4\"," +
                                "\"createdAt\":1537821669.000000000," +
                                "\"calculations\":38.0}"));

        verify(userService).getUserData("GraczykMateusz");
    }
}
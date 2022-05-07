package dev.graczykmateusz.restexercise.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{login}")
    public User getUserData(@PathVariable("login") String login) {
        userService.updateUserLoginStatistics(login);
        return userService.getUserData(login);
    }
}

package kma.topic2.junit.tests;

import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.exceptions.UserNotFoundException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.service.UserService;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    @SpyBean
    private UserRepository userRepository;
    @SpyBean
    private UserValidator userValidator;

    @ParameterizedTest
    @MethodSource("testUserCreationDataProvider")
    void shouldCreateAndGetUser(String fullName, String login, String password) {
        userService.createNewUser(
                NewUser.builder()
                    .fullName(fullName)
                    .login(login)
                    .password(password)
                    .build()
        );
        Mockito.verify(userValidator).validateNewUser(
                NewUser.builder()
                        .fullName(fullName)
                        .login(login)
                        .password(password)
                        .build()
        );
        Mockito.verify(userRepository).saveNewUser(
                NewUser.builder()
                        .fullName(fullName)
                        .login(login)
                        .password(password)
                        .build()
        );
    }

    @ParameterizedTest
    @MethodSource("testUserCreationDataProvider")
    void shouldThrowExceptionWhenCreatingUserWithExistingLogin(String fullName, String login, String password) {
        assertThatThrownBy(() -> userService.createNewUser(
                NewUser.builder()
                        .fullName(fullName)
                        .login(login)
                        .password(password)
                        .build()
        ))
                .isInstanceOf(LoginExistsException.class);
    }

    // We can increase amount of arguments for tests or change them easily with the help of this provider
    private static Stream<Arguments> testUserCreationDataProvider() {
        return Stream.of(
                Arguments.of("name", "login", "pass")
        );
    }

    @ParameterizedTest
    @MethodSource("testGettingUserDataProvider")
    void shouldGetUser(String login) {
        userService.getUserByLogin(login);
        Mockito.verify(userRepository).getUserByLogin(login);
    }


    // We can increase amount of arguments for tests or change them easily with the help of this provider
    private static Stream<String> testGettingUserDataProvider() {
        return Stream.of(
                "login1",
                "login2",
                "login3",
                "login"
        );
    }



    @ParameterizedTest
    @MethodSource("testThrowingExceptionWhenGetUser")
    void shouldThrowExceptionWhenGetUser(String login) {
        assertThatThrownBy(() -> userService.getUserByLogin(login))
                .isInstanceOf(UserNotFoundException.class);
    }

    // We can increase amount of arguments for tests or change them easily with the help of this provider
    private static Stream<String> testThrowingExceptionWhenGetUser() {
        return Stream.of(
                "aeg1",
                "aeg2",
                "aeg3"
        );
    }

    @ParameterizedTest
    @MethodSource("testUserCreationDataProvider")
    void shouldReturnCorrectData(String fullName, String login, String password) {
        assertThat(userRepository.getUserByLogin(login))
                .returns(fullName, User::getFullName)
                .returns(password, User::getPassword)
                .returns(login, User::getLogin);
    }



}

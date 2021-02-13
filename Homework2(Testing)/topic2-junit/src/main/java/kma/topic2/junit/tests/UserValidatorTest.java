package kma.topic2.junit.tests;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserValidator userValidator;

    @ParameterizedTest
    @MethodSource("testPassValidationDataProvider")
    void shouldPassValidation(String fullName,String login, String password) {
        userValidator.validateNewUser(
                NewUser.builder()
                        .fullName(fullName)
                        .login(login)
                        .password(password)
                        .build()
        );
        Mockito.verify(userRepository).isLoginExists(login);
    }

    private static Stream<Arguments> testPassValidationDataProvider() {
        return Stream.of(
                Arguments.of("fullName1", "login1", "pass1"),
                Arguments.of("fullName2", "login2", "pass2"),
                Arguments.of("fullName3", "login3", "pass3")
        );
    }


    @Test
    void shouldThrowException_whenLoginExists() {

        Mockito.when(userRepository.isLoginExists(ArgumentMatchers.anyString())).thenReturn(true);

        assertThatThrownBy(() -> userValidator.validateNewUser(
                NewUser.builder()
                        .fullName("aeg")
                        .login("log")
                        .password("pass")
                        .build()
        ))
                .isInstanceOf(LoginExistsException.class);
    }

    @ParameterizedTest
    @MethodSource("testPasswordDataProvider")
    void shouldThrowException_whenPasswordInvalid(String password, List<String> errors) {
        assertThatThrownBy(() -> userValidator.validateNewUser(
                NewUser.builder()
                        .fullName("aeg")
                        .login("log")
                        .password(password)
                        .build()
        ))
                .isInstanceOfSatisfying(
                            ConstraintViolationException.class,
                            ex -> assertThat(ex.getErrors()).isEqualTo(errors)
                        );
    }

    private static Stream<Arguments> testPasswordDataProvider() {
        return Stream.of(
                Arguments.of("pa", List.of("Password has invalid size")),
                Arguments.of("padwadsawad", List.of("Password has invalid size")),
                Arguments.of("padwadsawad@#@$@#", List.of("Password has invalid size","Password doesn't match regex")),
                Arguments.of("$", List.of("Password has invalid size","Password doesn't match regex"))
        );
    }
}

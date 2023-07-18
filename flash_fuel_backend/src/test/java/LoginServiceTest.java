import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.LoginService;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService loginService;
    private UserManager userManager;

    @BeforeEach
    public void setup() {
        // Setup user manager and login service before each test
        userManager = new UserManager();
        loginService = new LoginService(userManager);
    }

    @Test
    public void testGetUserProfileSuccess() {
        // Register user first
        UserCredentials user = new UserCredentials("username", "password", null);
        user.setId(userManager.generateNewUserId());
        userManager.registerUser(user);

        // Test successful login
        UserCredentials returnedUser = loginService.getUserProfile("username", "password");
        assertEquals(user, returnedUser);
    }

    @Test
    public void testGetUserProfileFailureDueToWrongPassword() {
        // Register user first
        UserCredentials user = new UserCredentials("username", "password", null);
        user.setId(userManager.generateNewUserId());
        userManager.registerUser(user);

        // Test unsuccessful login due to wrong password
        Exception exception = assertThrows(RuntimeException.class, () -> {
            loginService.getUserProfile("username", "wrongpassword");
        });
        assertEquals("Invalid username or password", exception.getMessage());
    }

    @Test
    public void testGetUserProfileFailureDueToNonExistentUser() {
        // Test unsuccessful login due to non-existent user
        Exception exception = assertThrows(RuntimeException.class, () -> {
            loginService.getUserProfile("nonexistentuser", "password");
        });
        assertEquals("Invalid username or password", exception.getMessage());
    }
}



/*
import com.flashfuel.project.model.User;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginServiceTest {

    private LoginService loginService;
    private UserManager userManager;

    @BeforeEach
    public void setup() {
        userManager = new UserManager();
        loginService = new LoginService(userManager);
    }

    @Test
    public void testGetProfile_ValidCredentials_ReturnsUser() {
        String username = "testUser";
        String password = "testPassword";
        userManager.registerUser(username, password); // Register the test user
        User expectedUser = userManager.getUserByUsername(username);

        User result = loginService.getProfile(username, password);

        Assertions.assertEquals(expectedUser, result);
    }

    @Test
    public void testGetProfile_InvalidCredentials_ReturnsNull() {
        String username = "testUser";
        String password = "testPassword";
        userManager.registerUser(username, "anotherPassword");

        User result = loginService.getProfile(username, password);

        Assertions.assertNull(result);
    }

    @Test
    public void testGetProfile_UserNotRegistered_ReturnsNull() {
        String username = "testUser";
        String password = "testPassword";

        User result = loginService.getProfile(username, password);

        Assertions.assertNull(result);
    }

    @Test
    public void testGenerateAuthToken_ReturnsNonEmptyToken() {
        String authToken = loginService.generateAuthToken();

        Assertions.assertNotNull(authToken);
        Assertions.assertNotEquals("", authToken);
    }
}
 */
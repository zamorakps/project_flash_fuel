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

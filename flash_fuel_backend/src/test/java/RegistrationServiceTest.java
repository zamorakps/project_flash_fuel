/*
import com.flashfuel.project.UserManager;
import com.flashfuel.project.service.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationServiceTest {

    private RegistrationService registrationService;
    private UserManager userManager;

    @BeforeEach
    public void setup() {
        userManager = new UserManager();
        registrationService = new RegistrationService(userManager);
    }

    @Test
    public void testRegisterUser_NewUsername_ReturnsTrue() {
        String username = "testUser";
        String password = "testPassword";

        boolean result = registrationService.registerUser(username, password);

        Assertions.assertTrue(result);
    }

    @Test
    public void testRegisterUser_ExistingUsername_ReturnsFalse() {
        String username = "existingUser";
        String password = "testPassword";
        userManager.registerUser(username, password);

        boolean result = registrationService.registerUser(username, password);

        Assertions.assertFalse(result);
    }

    @Test
    public void testRegisterUser_EmptyUsername_ReturnsFalse() {
        String username = "";
        String password = "testPassword";

        boolean result = registrationService.registerUser(username, password);

        Assertions.assertFalse(result);
    }

    @Test
    public void testRegisterUser_NullPassword_ReturnsFalse() {
        String username = "testUser";

        boolean result = registrationService.registerUser(username, null);

        Assertions.assertFalse(result);
    }
}
 */
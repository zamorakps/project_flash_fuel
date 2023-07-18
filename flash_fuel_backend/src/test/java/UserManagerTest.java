/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.UserCredentials;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {
    private UserManager userManager;

    @BeforeEach
    public void setUp() {
        userManager = new UserManager();
    }

    @Test
    public void testGenerateNewUserId() {
        long firstId = userManager.generateNewUserId();
        long secondId = userManager.generateNewUserId();
        assertTrue(secondId > firstId);
    }

    @Test
    public void testRegisterUser() {
        UserCredentials user = new UserCredentials("user1", "password1", null);
        userManager.registerUser(user);
        assertEquals(user, userManager.getUserByUsername("user1"));
    }

    @Test
    public void testIsUserRegistered() {
        UserCredentials user = new UserCredentials("user1", "password1", null);
        assertFalse(userManager.isUserRegistered(user.getId()));
        userManager.registerUser(user);
        assertTrue(userManager.isUserRegistered(user.getId()));
    }

    @Test
    public void testIsValidCredentials() {
        UserCredentials user = new UserCredentials("user1", "password1", null);
        assertFalse(userManager.isValidCredentials("user1", "password1"));
        userManager.registerUser(user);
        assertTrue(userManager.isValidCredentials("user1", "password1"));
    }
}
 */
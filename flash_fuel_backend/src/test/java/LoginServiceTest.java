/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.LoginService;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private UserManager userManager;
    private LoginService loginService;

    @BeforeEach
    public void setup() {
        userManager = new UserManager();
        loginService = new LoginService(userManager);
    }

    @Test
    public void testGetUserProfile() {
        String username = "user1";
        String password = "password123";
    
        // Create non-null ClientInformation
        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setName("Client 1");
        clientInformation.setAddress("Address 1");
        clientInformation.setCity("City 1");
        clientInformation.setState("State 1");
        clientInformation.setZipCode("ZipCode 1");
    
        // Ensure that username, password, and clientInformation are not null
        assertNotNull(username);
        assertNotNull(password);
        assertNotNull(clientInformation);
    
        UserCredentials user = new UserCredentials(username, password, clientInformation);
    
        // Ensure that user is not null
        assertNotNull(user);
    
        userManager.registerUser(user);
    
        UserCredentials fetchedUser = loginService.getUserProfile(username, password);
    
        // Ensure that fetchedUser is not null and equals the registered user
        assertNotNull(fetchedUser);
        assertEquals(user, fetchedUser);
    }    

    @Test
    public void testGetUserProfileInvalidCredentials() {
        assertThrows(RuntimeException.class, () -> {
            loginService.getUserProfile("invalid", "invalid");
        });
    }
}
*/

/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.LoginService;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private UserManager userManager;
    private LoginService loginService;

    @BeforeEach
    public void setup() {
        userManager = new UserManager();
        loginService = new LoginService(userManager);
    }

    @Test
    public void testGetUserProfile() {
        String username = "user1";
        String password = "password123";
    
        // Create non-null ClientInformation
        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setName("Client 1");
        clientInformation.setAddress("Address 1");
        clientInformation.setCity("City 1");
        clientInformation.setState("State 1");
        clientInformation.setZipCode("ZipCode 1");
    
        UserCredentials user = new UserCredentials(username, password, clientInformation);
    
        userManager.registerUser(user);
    
        UserCredentials fetchedUser = loginService.getUserProfile(username, password);
    
        assertNotNull(fetchedUser);
        assertEquals(user, fetchedUser);
    }    

    @Test
    public void testGetUserProfileInvalidCredentials() {
        assertThrows(RuntimeException.class, () -> {
            loginService.getUserProfile("invalid", "invalid");
        });
    }
}
*/



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
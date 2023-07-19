import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.LoginService;

public class LoginServiceTest {

    @Mock
    private UserManager userManager;

    @InjectMocks
    private LoginService loginService;

    private UserCredentials validUser;
    private UserCredentials invalidUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        validUser = new UserCredentials("ValidUsername", "ValidPassword", new ClientInformation());
        invalidUser = new UserCredentials("InvalidUsername", "InvalidPassword", new ClientInformation());

        when(userManager.getUserByUsername("ValidUsername")).thenReturn(validUser);
        when(userManager.getUserByUsername("InvalidUsername")).thenReturn(null);
        when(userManager.isValidCredentials("ValidUsername", "ValidPassword")).thenReturn(true);
        when(userManager.isValidCredentials("InvalidUsername", "InvalidPassword")).thenReturn(false);
    }

    @Test
    public void testGetProfile_ValidCredentials_ReturnsUser() {
        UserCredentials result = loginService.getUserProfile("ValidUsername", "ValidPassword");
        assertNotNull(result);
        assertEquals("ValidUsername", result.getUsername());
    }

    @Test
    public void testGetProfile_InvalidCredentials_ReturnsNull() {
        assertThrows(RuntimeException.class, () -> {
            loginService.getUserProfile("InvalidUsername", "InvalidPassword");
        });
    }

    @Test
    public void testGetProfile_UserNotRegistered_ReturnsNull() {
        assertThrows(RuntimeException.class, () -> {
            loginService.getUserProfile("NonExistentUsername", "NonExistentPassword");
        });
    }

    @Test
    public void testGenerateAuthToken_ReturnsNonEmptyToken() {
        String token = loginService.generateAuthToken();
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
}


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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.RegistrationService;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {

    private UserManager userManager;
    private RegistrationService registrationService;

    @BeforeEach
    public void setup() {
        userManager = new UserManager();
        registrationService = new RegistrationService(userManager);
    }

    @Test
    public void testRegisterUser() {
        String username = "user1";
        String password = "password123";

        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        clientInformationDTO.setId(1L);
        clientInformationDTO.setName("Client 1");
        clientInformationDTO.setAddress("Address 1");
        clientInformationDTO.setCity("City 1");
        clientInformationDTO.setState("State 1");
        clientInformationDTO.setZipCode("ZipCode 1");

        registrationService.registerUser(username, password, clientInformationDTO);

        UserCredentials user = userManager.getUserByUsername(username);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getClientInformation());
        assertEquals(clientInformationDTO.getName(), user.getClientInformation().getName());
    }

    @Test
    public void testIsUsernameTaken() {
        assertFalse(registrationService.isUsernameTaken("user1"));

        registrationService.registerUser("user1", "password123", new ClientInformationDTO());

        assertTrue(registrationService.isUsernameTaken("user1"));
    }
}


/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.service.RegistrationService;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {

    private UserManager userManager;
    private RegistrationService registrationService;

    @BeforeEach
    public void setup() {
        userManager = new UserManager();
        registrationService = new RegistrationService(userManager);
    }

    @Test
    public void registerUser() {
        String username = "testUsername";
        String password = "testPassword123";
        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setName("Test");

        assertDoesNotThrow(() -> registrationService.registerUser(username, password, clientInformation));
        assertNotNull(userManager.getUserByUsername(username));
    }

    @Test
    public void isUsernameTaken() {
        String username = "testUsername";
        String password = "testPassword123";
        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setName("Test");

        assertFalse(registrationService.isUsernameTaken(username));
        registrationService.registerUser(username, password, clientInformation);
        assertTrue(registrationService.isUsernameTaken(username));
    }

    @Test
    public void isUsernameAndPasswordValid() {
        String validUsername = "testUsername";
        String validPassword = "testPassword123";
        assertTrue(registrationService.isUsernameAndPasswordValid(validUsername, validPassword));

        String invalidUsername = "";
        String invalidPassword = "12345";
        assertFalse(registrationService.isUsernameAndPasswordValid(invalidUsername, validPassword));
        assertFalse(registrationService.isUsernameAndPasswordValid(validUsername, invalidPassword));
        assertFalse(registrationService.isUsernameAndPasswordValid(invalidUsername, invalidPassword));
    }
}
*/




/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.RegistrationService;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    private UserManager userManager;
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        registrationService = new RegistrationService(userManager);
    }

    @Test
    void isUsernameTaken() {
        // Scenario: no users are registered
        assertFalse(registrationService.isUsernameTaken("username"));

        // Scenario: user is registered
        userManager.registerUser(new UserCredentials("username", "password", null));
        assertTrue(registrationService.isUsernameTaken("username"));
    }

    @Test
    void registerUser() {
        ClientInformation clientInformation = new ClientInformation("John Doe", "123 St", "", "NYC", "NY", "10001");
        
        // Scenario: Valid registration
        assertDoesNotThrow(() -> registrationService.registerUser("username", "password123", clientInformation));
        assertNotNull(userManager.getUserByUsername("username"));
        
        // Scenario: Username already taken
        assertThrows(RuntimeException.class, () -> registrationService.registerUser("username", "password123", clientInformation));
        
        // Scenario: Invalid username and password
        assertThrows(RuntimeException.class, () -> registrationService.registerUser("", "123", clientInformation));
    }
}
*/


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
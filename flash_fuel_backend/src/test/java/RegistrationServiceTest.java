import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.RegistrationService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UserManager userManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerUserTest() {
        String username = "test";
        String password = "password";
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        
        doNothing().when(userManager).registerUser(any());
        when(userManager.getUserByUsername(any())).thenReturn(null);

        registrationService.registerUser(username, password, clientInformationDTO);

        verify(userManager, times(1)).registerUser(any());
    }

    @Test
    public void registerUserUsernameTakenTest() {
        String username = "test";
        String password = "password";
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        
        UserCredentials userCredentials = new UserCredentials();
        when(userManager.getUserByUsername(username)).thenReturn(userCredentials);

        assertThrows(RuntimeException.class, () -> registrationService.registerUser(username, password, clientInformationDTO));
    }

    @Test
    public void registerUserInvalidUsernameAndPasswordTest() {
        String username = ""; // Invalid username
        String password = "abc"; // Invalid password (length < 6)
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();

        assertThrows(RuntimeException.class, () -> registrationService.registerUser(username, password, clientInformationDTO));
    }

    @Test
    public void isUsernameTakenTest() {
        String username = "test";
        
        UserCredentials userCredentials = new UserCredentials();
        when(userManager.getUserByUsername(username)).thenReturn(userCredentials);

        boolean isTaken = registrationService.isUsernameTaken(username);

        assertTrue(isTaken); // This assertion checks if the username is taken
    }

    @Test
    public void isUsernameAndPasswordValidTest() {
        String username = ""; // Invalid username
        String password = "abc"; // Invalid password (length < 6)
        
        boolean isValid = registrationService.isUsernameAndPasswordValid(username, password);

        assertFalse(isValid); // This assertion checks if the username and password are not valid
    }
}

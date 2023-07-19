import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {

    private RegistrationService registrationService;
    private UserManager userManager;

    @BeforeEach
    public void setUp() {
        userManager = new UserManager();
        registrationService = new RegistrationService(userManager);
    }

    @Test
    public void testRegisterUser_NewUsername_ReturnsTrue() {
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        registrationService.registerUser("newuser", "password123", clientInformationDTO);
        UserCredentials user = userManager.getUserByUsername("newuser");
        assertNotNull(user);
    }

    @Test
    public void testRegisterUser_ExistingUsername_ReturnsFalse() {
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        registrationService.registerUser("existinguser", "password123", clientInformationDTO);
        assertThrows(RuntimeException.class, () -> registrationService.registerUser("existinguser", "password123", clientInformationDTO));
    }

    @Test
    public void testRegisterUser_EmptyUsername_ReturnsFalse() {
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        assertThrows(RuntimeException.class, () -> registrationService.registerUser("", "password123", clientInformationDTO));
    }

    @Test
    public void testRegisterUser_NullPassword_ReturnsFalse() {
        ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
        assertThrows(RuntimeException.class, () -> registrationService.registerUser("username", null, clientInformationDTO));
    }
}
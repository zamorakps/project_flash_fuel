import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformationDTO;
import com.flashfuel.project.service.RegistrationService;

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
}

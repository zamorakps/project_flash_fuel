import com.flashfuel.project.service.RegistrationService;
import com.flashfuel.project.model.UserCredentialsDTO;
import com.flashfuel.project.controller.RegistrationController;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private RegistrationService registrationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerTest() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername("test");
        userCredentialsDTO.setPassword("password");
    
        doNothing().when(registrationService).registerUser(any(), any(), any());
    
        ResponseEntity<?> result = registrationController.register(userCredentialsDTO);
    
        verify(registrationService, times(1)).registerUser(any(), any(), any());
        assertEquals(200, result.getStatusCode().value());
    } 
}

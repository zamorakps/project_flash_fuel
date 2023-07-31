import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flashfuel.project.ClientInformationManager;
import com.flashfuel.project.ClientInformationRepository;
import com.flashfuel.project.UserCredentialsRepository;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.ClientInformationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class ClientInformationServiceTest {

    @InjectMocks
    private ClientInformationService clientInformationService;

    @Mock
    private ClientInformationManager clientInformationManager;

    @Mock
    private ClientInformationRepository clientInformationRepository;

    @Mock
    private UserCredentialsRepository userCredentialsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getClientInformationByUserIdTest() {
        // given
        Long userId = 1L;
        ClientInformation clientInformation = new ClientInformation();
        
        when(clientInformationManager.getClientInformationByUserId(userId)).thenReturn(clientInformation);

        // when
        ClientInformation found = clientInformationService.getClientInformationByUserId(userId);

        // then
        assertEquals(clientInformation, found);
    }

    @Test
    public void updateClientInformationTest() {
        // given
        Long userId = 1L;
        UserCredentials user = new UserCredentials();
        user.setId(userId);
        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setName("Test Name");
        clientInformation.setAddress("Test Address");
        clientInformation.setCity("Test City");
        clientInformation.setState("Test State");
        clientInformation.setZipCode("12345");
        user.setClientInformation(clientInformation);
        
        when(userCredentialsRepository.findById(userId)).thenReturn(Optional.of(user));
        when(clientInformationRepository.save(any(ClientInformation.class))).thenReturn(clientInformation);
    
        // when
        String errors = clientInformationService.updateClientInformation(userId, clientInformation);
    
        // then
        assertNull(errors);
    }    
}

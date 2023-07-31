import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.config.TokenProvider;
import com.flashfuel.project.controller.ClientInformationController;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientInformationControllerTest {

    @InjectMocks
    private ClientInformationController clientInformationController;

    @Mock
    private ClientInformationService clientInformationService;

    @Mock
    private TokenProvider tokenProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getClientInformationTest() {
        // given
        String authHeader = "Bearer valid_token"; // replace with a valid token
        ClientInformation clientInformation = new ClientInformation();
    
        when(tokenProvider.getIdFromToken(any())).thenReturn(1L);  // Add this line
        when(clientInformationService.getClientInformationByUserId(anyLong())).thenReturn(clientInformation);
    
        // when
        ResponseEntity<ClientInformation> result = clientInformationController.getClientInformation(authHeader);
    
        // then
        verify(clientInformationService, times(1)).getClientInformationByUserId(anyLong());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(clientInformation, result.getBody());
    }
    
    @Test
    public void updateClientInformationTest() {
        // given
        String authHeader = "Bearer valid_token"; // replace with a valid token
        ClientInformation clientInformation = new ClientInformation();
    
        when(tokenProvider.getIdFromToken(any())).thenReturn(1L);  // Add this line
        when(clientInformationService.updateClientInformation(anyLong(), any())).thenReturn(null);
    
        // when
        ResponseEntity<?> result = clientInformationController.updateClientInformation(authHeader, clientInformation);
    
        // then
        verify(clientInformationService, times(1)).updateClientInformation(anyLong(), any());
        assertEquals(200, result.getStatusCode().value());
    }
}

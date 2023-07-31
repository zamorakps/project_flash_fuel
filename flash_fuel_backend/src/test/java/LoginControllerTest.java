import com.flashfuel.project.service.LoginService;
import com.flashfuel.project.model.UserCredentialsDTO;
import com.flashfuel.project.controller.LoginController;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loginTest() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername("test");
        userCredentialsDTO.setPassword("password");

        Map<String, Object> response = new HashMap<>();
        response.put("userProfile", userCredentialsDTO);
        response.put("token", "token");

        when(loginService.loginAndGetUserProfile(any(), any())).thenReturn(response);

        ResponseEntity<?> result = loginController.login(userCredentialsDTO);

        verify(loginService, times(1)).loginAndGetUserProfile(any(), any());
        assertEquals(200, result.getStatusCode().value());
    }
}

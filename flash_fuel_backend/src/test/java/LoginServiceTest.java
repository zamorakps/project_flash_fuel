import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.config.TokenProvider;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.LoginService;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserManager userManager;

    @Mock
    private TokenProvider tokenProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loginAndGetUserProfileTest() {
        String username = "test";
        String password = "password";
        Long id = 1L; // or some other non-null value that is appropriate for your use case

        UserCredentials user = new UserCredentials();
        user.setUsername(username);
        user.setPassword(password);
        user.setId(id); // Set the ID here

        when(userManager.isValidCredentials(any(), any())).thenReturn(true);
        when(userManager.getUserByUsername(any())).thenReturn(user);
        when(tokenProvider.generateToken(anyString(), anyLong())).thenReturn("token");

        Map<String, Object> result = loginService.loginAndGetUserProfile(username, password);

        verify(userManager, times(1)).isValidCredentials(username, password);
        verify(userManager, times(1)).getUserByUsername(username);
        verify(tokenProvider, times(1)).generateToken(anyString(), anyLong());

        assert result.get("userProfile").equals(user);
        assert result.get("token").equals("token");
    }
}
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.flashfuel.project.UserManager;
// import com.flashfuel.project.model.ClientInformation;
// import com.flashfuel.project.model.UserCredentials;
// import com.flashfuel.project.service.LoginService;

// public class LoginServiceTest {

//     @Mock
//     private UserManager userManager;

//     @InjectMocks
//     private LoginService loginService;

//     private UserCredentials validUser;
//     private UserCredentials invalidUser;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);

//         validUser = new UserCredentials("ValidUsername", "ValidPassword", new ClientInformation());
//         invalidUser = new UserCredentials("InvalidUsername", "InvalidPassword", new ClientInformation());

//         when(userManager.getUserByUsername("ValidUsername")).thenReturn(validUser);
//         when(userManager.getUserByUsername("InvalidUsername")).thenReturn(null);
//         when(userManager.isValidCredentials("ValidUsername", "ValidPassword")).thenReturn(true);
//         when(userManager.isValidCredentials("InvalidUsername", "InvalidPassword")).thenReturn(false);
//     }

//     @Test
//     public void testGetProfile_ValidCredentials_ReturnsUser() {
//         UserCredentials result = loginService.getUserProfile("ValidUsername", "ValidPassword");
//         assertNotNull(result);
//         assertEquals("ValidUsername", result.getUsername());
//     }

//     @Test
//     public void testGetProfile_InvalidCredentials_ReturnsNull() {
//         assertThrows(RuntimeException.class, () -> {
//             loginService.getUserProfile("InvalidUsername", "InvalidPassword");
//         });
//     }

//     @Test
//     public void testGetProfile_UserNotRegistered_ReturnsNull() {
//         assertThrows(RuntimeException.class, () -> {
//             loginService.getUserProfile("NonExistentUsername", "NonExistentPassword");
//         });
//     }

//     @Test
//     public void testGenerateAuthToken_ReturnsNonEmptyToken() {
//         String token = loginService.generateAuthToken();
//         assertNotNull(token);
//         assertFalse(token.isEmpty());
//     }
// }

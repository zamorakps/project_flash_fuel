// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.flashfuel.project.ClientInformationManager;
// import com.flashfuel.project.UserManager;
// import com.flashfuel.project.model.ClientInformation;
// import com.flashfuel.project.model.ClientInformationDTO;
// import com.flashfuel.project.model.UserCredentials;
// import com.flashfuel.project.service.ClientInformationService;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// public class ClientInformationServiceTest {

//     private ClientInformationService clientInformationService;

//     @Mock
//     private ClientInformationManager clientInformationManager;

//     @Mock
//     private UserManager userManager;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);
//         clientInformationService = new ClientInformationService(clientInformationManager);
//     }

//     @Test
//     public void testGetProfile_ValidCredentials_ReturnsUser() {
//         ClientInformation clientInfo = new ClientInformation("John Doe", "123 Street", "Apt 4B", "New York", "NY", "10001");
//         UserCredentials userCredentials = new UserCredentials("johnDoe", "password123", clientInfo);
//         Long userId = 1L;
//         when(userManager.getUserById(userId)).thenReturn(userCredentials);
//         when(clientInformationManager.getClientInformationByUserId(userId)).thenReturn(clientInfo);

//         ClientInformation result = clientInformationService.getClientInformationByUserId(userId);
//         assertNotNull(result);
//         assertEquals("John Doe", result.getName());
//     }

//     @Test
//     public void testGetProfile_InvalidCredentials_ReturnsNull() {
//         Long userId = 1L;
//         when(userManager.getUserById(userId)).thenReturn(null);

//         ClientInformation result = clientInformationService.getClientInformationByUserId(userId);
//         assertNull(result);
//     }

//     @Test
//     public void testGetProfile_UserNotRegistered_ReturnsNull() {
//         Long userId = 1L;
//         when(clientInformationManager.getClientInformationByUserId(userId)).thenReturn(null);

//         ClientInformation result = clientInformationService.getClientInformationByUserId(userId);
//         assertNull(result);
//     }

//     @Test
//     public void testUpdateClientInformation() {
//         ClientInformation clientInfo = new ClientInformation("John Doe", "123 Street", "Apt 4B", "New York", "NY", "10001");
//         Long userId = 1L;
//         when(clientInformationManager.getClientInformationByUserId(userId)).thenReturn(clientInfo);

//         // Valid information
//         ClientInformation updateInfo = new ClientInformation("Jane Doe", "456 Avenue", "Apt 3C", "Los Angeles", "CA", "90001");
//         clientInformationService.updateClientInformation(userId, updateInfo);

//         verify(clientInformationManager, times(1)).updateClientInformation(userId, updateInfo);
//     }

//     @Test
//     public void testGetUpdateProfileErrors() {
//         ClientInformationDTO badInfo = new ClientInformationDTO();
//         badInfo.setName("");
//         badInfo.setAddress("");
//         badInfo.setCity("");
//         badInfo.setState("");
//         badInfo.setZipCode("1234");

//         String errors = clientInformationService.getUpdateProfileErrors(badInfo);
//         assertNotNull(errors);
//         assertTrue(errors.contains("Name is required."));
//         assertTrue(errors.contains("Address is required."));
//         assertTrue(errors.contains("City is required."));
//         assertTrue(errors.contains("State is required."));
//         assertTrue(errors.contains("Invalid Zip Code format."));
//     }
// }

/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.ClientInformationManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.service.ClientInformationService;

public class ClientInformationServiceTest {

    private ClientInformationService clientInformationService;
    private ClientInformationManager clientInformationManager;

    @BeforeEach
    public void setup() {
        clientInformationManager = new ClientInformationManager();
        clientInformationService = new ClientInformationService(clientInformationManager);
    }

    @Test
    public void testGetClientInformationByUserId() {
        ClientInformation clientInformation = new ClientInformation("John Doe", "123 Main St", "", "Los Angeles", "CA", "90001");
        long userId = 1L;
        clientInformationManager.addClientInformation(userId, clientInformation);

        ClientInformation retrievedClientInformation = clientInformationService.getClientInformationByUserId(userId);

        assertEquals(clientInformation, retrievedClientInformation);
    }

    @Test
    public void testUpdateClientInformationValidInfo() {
        ClientInformation clientInformation = new ClientInformation("John Doe", "123 Main St", "", "Los Angeles", "CA", "90001");
        long userId = 1L;
        clientInformationService.updateClientInformation(userId, clientInformation);

        ClientInformation updatedClientInformation = clientInformationService.getClientInformationByUserId(userId);

        assertEquals(clientInformation, updatedClientInformation);
    }

    @Test
    public void testUpdateClientInformationInvalidInfo() {
        ClientInformation clientInformation = new ClientInformation("", "", "", "", "", "");
        long userId = 1L;

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clientInformationService.updateClientInformation(userId, clientInformation);
        });

        String expectedMessage = "<ul><li>Name is required.</li><li>Address is required.</li><li>City is required.</li><li>State is required.</li><li>Zip Code is required.</li></ul>";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
*/
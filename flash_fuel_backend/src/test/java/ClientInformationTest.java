/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;

import static org.junit.jupiter.api.Assertions.*;

public class ClientInformationTest {
    private UserCredentials user;
    private ClientInformation clientInformation;

    @BeforeEach
    public void setUp() {
        user = new UserCredentials("user1", "password1", null);
        clientInformation = new ClientInformation("John Doe", "123 Main St", "Apt 4B", "New York", "NY", "10001");
    }

    @Test
    public void testSetUserCredentialsWithUpdate() {
        clientInformation.setUserCredentials(user, true);
        assertEquals(clientInformation, user.getClientInformation());
        assertEquals(user, clientInformation.getUserCredentials());
    }

    @Test
    public void testSetUserCredentialsWithoutUpdate() {
        clientInformation.setUserCredentials(user, false);
        assertNull(user.getClientInformation());
        assertEquals(user, clientInformation.getUserCredentials());
    }

    @Test
    public void testSetUserCredentialsToNull() {
        clientInformation.setUserCredentials(user, true);
        clientInformation.setUserCredentials(null, false);
        assertNull(clientInformation.getUserCredentials());
        assertNull(user.getClientInformation());
    }
}
 */
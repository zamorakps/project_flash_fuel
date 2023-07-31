import com.flashfuel.project.ClientInformationManager;
import com.flashfuel.project.ClientInformationRepository;
import com.flashfuel.project.ProjectApplication;
import com.flashfuel.project.UserCredentialsRepository;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.UserCredentials;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ClientInformationManagerTest {

    @MockBean
    private UserCredentialsRepository userCredentialsRepository;

    @MockBean
    private ClientInformationRepository clientInformationRepository;

    private ClientInformationManager clientInformationManager;

    @BeforeEach
    public void setup() {
        clientInformationManager = new ClientInformationManager(clientInformationRepository, userCredentialsRepository);
    }

    @Test
    public void getClientInformationByUserIdTest() {
        // given
        Long userId = 1L;
        UserCredentials user = new UserCredentials();
        user.setId(userId);
        ClientInformation clientInformation = new ClientInformation();
        user.setClientInformation(clientInformation);
        
        when(userCredentialsRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        ClientInformation found = clientInformationManager.getClientInformationByUserId(userId);

        // then
        assertThat(found).isEqualTo(clientInformation);
    }
}

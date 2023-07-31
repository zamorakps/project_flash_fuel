import com.flashfuel.project.ProjectApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

import com.flashfuel.project.ClientInformationRepository;
import com.flashfuel.project.model.ClientInformation;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ClientInformationRepositoryTest {

    @Autowired
    private ClientInformationRepository clientInformationRepository;

    @AfterEach
    public void cleanUp(){
        clientInformationRepository.deleteAll();
    }

    @Test
    public void whenSaveClient_thenRetrieveSavedClient() {
        // given
        ClientInformation client = new ClientInformation("John Doe", "123 Main St", "Apt 4B", "Springfield", "Illinois", "62701");
        clientInformationRepository.save(client);

        // when
        ClientInformation found = clientInformationRepository.findById(client.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(client.getName());
    }
}

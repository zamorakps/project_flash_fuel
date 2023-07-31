import com.flashfuel.project.ProjectApplication;
import com.flashfuel.project.UserCredentialsRepository;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.UserManager;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UserManagerTest {

    @MockBean
    private UserCredentialsRepository userCredentialsRepository;

    private UserManager userManager;

    @BeforeEach
    public void setup() {
        userManager = new UserManager(userCredentialsRepository);
    }

    @Test
    public void whenValidUsername_thenUserShouldBeFound() {
        // given
        UserCredentials alex = new UserCredentials("alex", "password", null);
        when(userCredentialsRepository.findByUsername(alex.getUsername())).thenReturn(alex);

        // when
        UserCredentials found = userManager.getUserByUsername(alex.getUsername());

        // then
        assertThat(found.getUsername())
          .isEqualTo(alex.getUsername());
    }
}

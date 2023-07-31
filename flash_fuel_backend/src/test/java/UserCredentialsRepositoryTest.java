import com.flashfuel.project.ProjectApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

import com.flashfuel.project.UserCredentialsRepository;
import com.flashfuel.project.model.UserCredentials;

import org.junit.jupiter.api.*;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UserCredentialsRepositoryTest {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @AfterEach
    public void cleanUp(){
        userCredentialsRepository.deleteAll();
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        String uniqueUsername = "alex" + System.currentTimeMillis();
        UserCredentials user = new UserCredentials(uniqueUsername, "password", null);
        userCredentialsRepository.save(user);
    
        // when
        UserCredentials found = userCredentialsRepository.findByUsername(user.getUsername());
    
        // then
        assertThat(found.getUsername())
          .isEqualTo(user.getUsername());
    }
}



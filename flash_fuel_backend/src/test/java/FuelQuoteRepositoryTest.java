import com.flashfuel.project.ProjectApplication;
import com.flashfuel.project.UserCredentialsRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

import com.flashfuel.project.FuelQuoteRepository;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.UserCredentials;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class FuelQuoteRepositoryTest {

    @Autowired
    private FuelQuoteRepository fuelQuoteRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @AfterEach
    public void cleanUp(){
        fuelQuoteRepository.deleteAll();
        userCredentialsRepository.deleteAll();
    }

    @Test
    public void whenFindByUser_thenReturnFuelQuote() {
        // given
        UserCredentials user = new UserCredentials("username", "password", null);
        userCredentialsRepository.save(user);

        FuelQuote fuelQuote = new FuelQuote(user, 100, LocalDate.now(), 2.50, 250.0);
        fuelQuoteRepository.save(fuelQuote);
    
        // when
        List<FuelQuote> found = fuelQuoteRepository.findByUser(user);
    
        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getUser().getUsername()).isEqualTo(user.getUsername());
    }
}

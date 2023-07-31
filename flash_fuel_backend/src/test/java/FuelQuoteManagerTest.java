import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.FuelQuoteRepository;
import com.flashfuel.project.ProjectApplication;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.model.FuelQuote;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class FuelQuoteManagerTest {

    @MockBean
    private FuelQuoteRepository fuelQuoteRepository;

    private FuelQuoteManager fuelQuoteManager;

    @BeforeEach
    public void setup() {
        fuelQuoteManager = new FuelQuoteManager(fuelQuoteRepository);
    }

    @Test
    public void getFuelQuotesByUserIdTest() {
        // given
        Long userId = 1L;
        UserCredentials user = new UserCredentials();
        user.setId(userId);
        List<FuelQuote> quotes = new ArrayList<>();

        when(fuelQuoteRepository.findByUser(user)).thenReturn(quotes);

        // when
        List<FuelQuote> found = fuelQuoteManager.getFuelQuotesByUserId(userId);

        // then
        assertIterableEquals(quotes, found);
    }
}

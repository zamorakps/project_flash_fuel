import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.ProjectApplication;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.service.FuelQuoteService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class FuelQuoteServiceTest {

    @MockBean
    private FuelQuoteManager fuelQuoteManager;
    @MockBean
    private ClientInformationService clientInformationService;
    @MockBean
    private UserManager userManager;

    private FuelQuoteService fuelQuoteService;

    @BeforeEach
    public void setup() {
        fuelQuoteService = new FuelQuoteService(fuelQuoteManager, clientInformationService, userManager);
    }

    @Test
    public void getFuelQuotesByUserIdTest() {
        // given
        Long userId = 1L;
        List<FuelQuote> quotes = new ArrayList<>();

        when(fuelQuoteManager.getFuelQuotesByUserId(userId)).thenReturn(quotes);

        // when
        List<FuelQuote> found = fuelQuoteService.getFuelQuotesByUserId(userId);

        // then
        assertIterableEquals(quotes, found);
    }

    @Test
    public void addFuelQuoteTest() {
        // given
        Long userId = 1L;
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();
        fuelQuoteDTO.setGallonsRequested(2000);
        fuelQuoteDTO.setDeliveryDate(LocalDate.now().plusDays(10));
        fuelQuoteDTO.setSuggestedPrice(2.5);
        fuelQuoteDTO.setTotalAmountDue(5000.0);

        ClientInformation clientInformation = new ClientInformation();
        UserCredentials user = new UserCredentials();
        user.setId(userId);
        
        when(clientInformationService.getClientInformationByUserId(userId)).thenReturn(clientInformation);
        when(userManager.getUserById(userId)).thenReturn(user);

        // when
        fuelQuoteService.addFuelQuote(userId, fuelQuoteDTO);

        // then
        verify(fuelQuoteManager, times(1)).addFuelQuote(any(UserCredentials.class), any(FuelQuote.class));
    }

    @Test
    public void validateFuelQuoteTest() {
        // given
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();
        fuelQuoteDTO.setGallonsRequested(-10);
        fuelQuoteDTO.setDeliveryDate(LocalDate.now().minusDays(5));
        fuelQuoteDTO.setSuggestedPrice(-2.5);
        fuelQuoteDTO.setTotalAmountDue(-100.0);
    
        // when
        String errors = fuelQuoteService.validateFuelQuote(fuelQuoteDTO);
    
        // then
        assertNotNull(errors); // This assertion checks if the errors string is not null
        assertTrue(errors.contains("Gallons requested must be greater than 0."));
        assertTrue(errors.contains("Delivery date must be today's date or in the future."));
        assertTrue(errors.contains("Suggested price cannot be null or negative."));
        assertTrue(errors.contains("Total amount due cannot be null or negative."));
    }    


    @Test
    public void addFuelQuoteClientInfoNotFoundTest() {
        // given
        Long userId = 1L;
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();
        fuelQuoteDTO.setGallonsRequested(2000);
        fuelQuoteDTO.setDeliveryDate(LocalDate.now().plusDays(10));
        fuelQuoteDTO.setSuggestedPrice(2.5);
        fuelQuoteDTO.setTotalAmountDue(5000.0);
        
        when(clientInformationService.getClientInformationByUserId(userId)).thenReturn(null);

        // then
        assertThrows(RuntimeException.class, () -> fuelQuoteService.addFuelQuote(userId, fuelQuoteDTO));
    }

    @Test
    public void addFuelQuoteUserNotFoundTest() {
        // given
        Long userId = 1L;
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();
        fuelQuoteDTO.setGallonsRequested(2000);
        fuelQuoteDTO.setDeliveryDate(LocalDate.now().plusDays(10));
        fuelQuoteDTO.setSuggestedPrice(2.5);
        fuelQuoteDTO.setTotalAmountDue(5000.0);

        ClientInformation clientInformation = new ClientInformation();

        when(clientInformationService.getClientInformationByUserId(userId)).thenReturn(clientInformation);
        when(userManager.getUserById(userId)).thenReturn(null);

        // then
        assertThrows(RuntimeException.class, () -> fuelQuoteService.addFuelQuote(userId, fuelQuoteDTO));
    }

    @Test
    public void addFuelQuoteValidationErrorsTest() {
        // given
        Long userId = 1L;
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();
        fuelQuoteDTO.setGallonsRequested(-10);
        fuelQuoteDTO.setDeliveryDate(LocalDate.now().minusDays(5));
        fuelQuoteDTO.setSuggestedPrice(-2.5);
        fuelQuoteDTO.setTotalAmountDue(-100.0);

        ClientInformation clientInformation = new ClientInformation();
        UserCredentials user = new UserCredentials();
        user.setId(userId);

        when(clientInformationService.getClientInformationByUserId(userId)).thenReturn(clientInformation);
        when(userManager.getUserById(userId)).thenReturn(user);

        // then
        assertThrows(RuntimeException.class, () -> fuelQuoteService.addFuelQuote(userId, fuelQuoteDTO));
    }
    
    @Test
    public void validateFuelQuoteNullValuesTest() {
        // given
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();
        fuelQuoteDTO.setGallonsRequested(2000);
        fuelQuoteDTO.setDeliveryDate(LocalDate.now().plusDays(10));
        fuelQuoteDTO.setSuggestedPrice(null);
        fuelQuoteDTO.setTotalAmountDue(null);

        // when
        String errors = fuelQuoteService.validateFuelQuote(fuelQuoteDTO);

        // then
        assertNotNull(errors); // This assertion checks if the errors string is not null
        assertTrue(errors.contains("Suggested price cannot be null or negative."));
        assertTrue(errors.contains("Total amount due cannot be null or negative."));
    }
}

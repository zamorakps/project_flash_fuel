import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.UserCredentials;
import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.service.FuelQuoteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FuelQuoteServiceTest {
    private FuelQuoteManager fuelQuoteManager;
    private ClientInformationService clientInformationService;
    private UserManager userManager;
    private FuelQuoteService fuelQuoteService;

    @BeforeEach
    public void setUp() {
        fuelQuoteManager = mock(FuelQuoteManager.class);
        clientInformationService = mock(ClientInformationService.class);
        userManager = mock(UserManager.class);
        fuelQuoteService = new FuelQuoteService(fuelQuoteManager, clientInformationService, userManager);
    }

    @Test
    public void testGetFuelQuotesByUserId() {
        Long userId = 1L;
        List<FuelQuote> fuelQuotes = new ArrayList<>();
        FuelQuote fuelQuote = new FuelQuote();
        fuelQuotes.add(fuelQuote);
        when(fuelQuoteManager.getFuelQuotesByUserId(userId)).thenReturn(fuelQuotes);
        List<FuelQuote> result = fuelQuoteService.getFuelQuotesByUserId(userId);
        assertEquals(fuelQuotes, result);
    }

    @Test
    public void testCalculatePrices() {
        String gallonsRequestedStr = "10";
        FuelQuoteDTO fuelQuoteDTO = fuelQuoteService.calculatePrices(gallonsRequestedStr);
        assertEquals(Integer.valueOf(gallonsRequestedStr), fuelQuoteDTO.getGallonsRequested());
        assertEquals(2.0, fuelQuoteDTO.getSuggestedPrice());
        assertEquals(20.0, fuelQuoteDTO.getTotalAmountDue());
    }

    @Test
    public void testAddFuelQuote() {
        Long userId = 1L;
        FuelQuoteDTO fuelQuoteDto = new FuelQuoteDTO();
        fuelQuoteDto.setGallonsRequested(10);
        fuelQuoteDto.setDeliveryDate(LocalDate.now().plusDays(5));
        fuelQuoteDto.setSuggestedPrice(2.0);
        fuelQuoteDto.setTotalAmountDue(20.0);

        UserCredentials user = new UserCredentials();
        user.setId(userId);

        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setName("Test");

        when(clientInformationService.getClientInformationByUserId(userId)).thenReturn(clientInformation);
        when(userManager.getUserById(userId)).thenReturn(user);

        fuelQuoteService.addFuelQuote(userId, fuelQuoteDto);

        verify(fuelQuoteManager, times(1)).addFuelQuote(eq(user), any(FuelQuote.class));
    }

    @Test
    public void testValidateFuelQuote() {
        // Test with valid FuelQuoteDTO
        FuelQuoteDTO validFuelQuoteDto = new FuelQuoteDTO();
        validFuelQuoteDto.setGallonsRequested(10);
        validFuelQuoteDto.setDeliveryDate(LocalDate.now().plusDays(1));
        validFuelQuoteDto.setSuggestedPrice(2.5);
        validFuelQuoteDto.setTotalAmountDue(25.0);
        assertNull(fuelQuoteService.validateFuelQuote(validFuelQuoteDto));

        // Test with FuelQuoteDTO with zero gallons requested
        FuelQuoteDTO zeroGallonsDto = new FuelQuoteDTO();
        zeroGallonsDto.setGallonsRequested(0);
        zeroGallonsDto.setDeliveryDate(LocalDate.now().plusDays(1));
        zeroGallonsDto.setSuggestedPrice(2.5);
        zeroGallonsDto.setTotalAmountDue(25.0);
        assertNotNull(fuelQuoteService.validateFuelQuote(zeroGallonsDto));
        assertTrue(fuelQuoteService.validateFuelQuote(zeroGallonsDto).contains("Gallons requested must be greater than 0."));

        // Test with FuelQuoteDTO with delivery date in the past
        FuelQuoteDTO pastDeliveryDateDto = new FuelQuoteDTO();
        pastDeliveryDateDto.setGallonsRequested(10);
        pastDeliveryDateDto.setDeliveryDate(LocalDate.now().minusDays(1));
        pastDeliveryDateDto.setSuggestedPrice(2.5);
        pastDeliveryDateDto.setTotalAmountDue(25.0);
        assertNotNull(fuelQuoteService.validateFuelQuote(pastDeliveryDateDto));
        assertTrue(fuelQuoteService.validateFuelQuote(pastDeliveryDateDto).contains("Delivery date must be today's date or in the future."));

        // Test with FuelQuoteDTO with negative suggested price
        FuelQuoteDTO negativeSuggestedPriceDto = new FuelQuoteDTO();
        negativeSuggestedPriceDto.setGallonsRequested(10);
        negativeSuggestedPriceDto.setDeliveryDate(LocalDate.now().plusDays(1));
        negativeSuggestedPriceDto.setSuggestedPrice(-2.5);
        negativeSuggestedPriceDto.setTotalAmountDue(25.0);
        assertNotNull(fuelQuoteService.validateFuelQuote(negativeSuggestedPriceDto));
        assertTrue(fuelQuoteService.validateFuelQuote(negativeSuggestedPriceDto).contains("Suggested price cannot be null or negative."));

        // Test with FuelQuoteDTO with negative total amount due
        FuelQuoteDTO negativeTotalAmountDueDto = new FuelQuoteDTO();
        negativeTotalAmountDueDto.setGallonsRequested(10);
        negativeTotalAmountDueDto.setDeliveryDate(LocalDate.now().plusDays(1));
        negativeTotalAmountDueDto.setSuggestedPrice(2.5);
        negativeTotalAmountDueDto.setTotalAmountDue(-25.0);
        assertNotNull(fuelQuoteService.validateFuelQuote(negativeTotalAmountDueDto));
        assertTrue(fuelQuoteService.validateFuelQuote(negativeTotalAmountDueDto).contains("Total amount due cannot be null or negative."));
    }
}

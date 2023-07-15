import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.AddFuelQuoteResponse;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.User;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.ProfileManagementService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuelQuoteServiceTest {

    @Mock
    private FuelQuoteManager fuelQuoteManager;

    @Mock
    private UserManager userManager;

    @Mock
    private ProfileManagementService profileManagementService;

    @InjectMocks
    private FuelQuoteService fuelQuoteService;

    @Test
    void testAddFuelQuote() {
        FuelQuoteRequest request = new FuelQuoteRequest();
        request.setUsername("testUser");
        request.setGallonsRequested(10);
        request.setDeliveryAddress("123 Fake St");
        request.setDeliveryDate(LocalDate.now().plusDays(1));
        request.setSuggestedPrice(2.0);
        request.setTotalAmountDue(20.0);

        when(userManager.getUserByUsername("testUser")).thenReturn(new User());

        AddFuelQuoteResponse response = fuelQuoteService.addFuelQuote(request);

        assertEquals("Fuel quote added successfully.", response.getMessage());
        assertEquals(request.getSuggestedPrice(), response.getSuggestedPrice());
        assertEquals(request.getTotalAmountDue(), response.getTotalAmountDue());

        verify(fuelQuoteManager, times(1)).addFuelQuote(any(User.class), anyInt(), anyString(), any(LocalDate.class), anyDouble(), anyDouble());
    }

    @Test
    void testAddFuelQuoteInvalidRequest() {
        FuelQuoteRequest request = new FuelQuoteRequest();
        request.setUsername("testUser");
        request.setGallonsRequested(0);
        request.setDeliveryAddress("123 Fake St");
        request.setDeliveryDate(LocalDate.now().plusDays(1));
        request.setSuggestedPrice(2.0);
        request.setTotalAmountDue(20.0);

        AddFuelQuoteResponse response = fuelQuoteService.addFuelQuote(request);

        assertTrue(response.getMessage().contains("Gallons requested must be greater than 0."));
    }

    @Test
    void testGetFuelQuoteHistory() {
        when(fuelQuoteManager.getFuelQuotesByUsername("testUser")).thenReturn(List.of(new FuelQuote()));

        List<FuelQuoteResponse> fuelQuoteResponses = fuelQuoteService.getFuelQuoteHistory("testUser");

        assertFalse(fuelQuoteResponses.isEmpty());
        verify(fuelQuoteManager, times(1)).getFuelQuotesByUsername("testUser");
    }

    @Test
    void testTransformFuelQuoteToResponse() {
        User user = new User();
        user.setUsername("testUser");

        FuelQuote fuelQuote = new FuelQuote(user, 10, "123 Fake St", LocalDate.now(), 2.0, 20.0);

        FuelQuoteResponse response = fuelQuoteService.transformFuelQuoteToResponse(fuelQuote);

        assertEquals(fuelQuote.getUser().getUsername(), response.getUsername());
        assertEquals(fuelQuote.getGallonsRequested(), response.getGallonsRequested());
        assertEquals(fuelQuote.getDeliveryAddress(), response.getDeliveryAddress());
        assertEquals(fuelQuote.getDeliveryDate(), response.getDeliveryDate());
        assertEquals(fuelQuote.getSuggestedPrice(), response.getSuggestedPrice());
        assertEquals(fuelQuote.getTotalAmountDue(), response.getTotalAmountDue());
    }
}

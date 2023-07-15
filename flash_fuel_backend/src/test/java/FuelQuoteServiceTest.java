import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flashfuel.project.FuelQuoteManager;
import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.User;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.ProfileManagementService;

// Add imports for your classes

public class FuelQuoteServiceTest {

    @InjectMocks
    private FuelQuoteService fuelQuoteService;

    @Mock
    private FuelQuoteManager fuelQuoteManager;

    @Mock
    private UserManager userManager;

    @Mock
    private ProfileManagementService profileManagementService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFuelQuoteHistory() {
        // Set up test data
        User user = new User();
        user.setUsername("testUser");
        FuelQuote fuelQuote = new FuelQuote(user, 500, "123 Fake St", LocalDate.now(), 2.5, 1250.0);
        List<FuelQuote> fuelQuotes = new ArrayList<>(Arrays.asList(fuelQuote));

        // Configure mocks
        when(fuelQuoteManager.getFuelQuotesByUsername("testUser")).thenReturn(fuelQuotes);

        // Call the method under test
        List<FuelQuoteResponse> result = fuelQuoteService.getFuelQuoteHistory("testUser");

        // Verify interactions
        verify(fuelQuoteManager).getFuelQuotesByUsername("testUser");

        // Assert results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    public void testAddFuelQuote() {
        // Set up test data
        User user = new User();
        user.setUsername("testUser");
        FuelQuoteRequest request = new FuelQuoteRequest();
        request.setUsername("testUser");
        request.setGallonsRequested(500);
        request.setDeliveryAddress("123 Fake St");
        request.setDeliveryDate(LocalDate.now());
        request.setSuggestedPrice(2.5);
        request.setTotalAmountDue(1250.0);

        // Configure mocks
        when(userManager.getUserByUsername("testUser")).thenReturn(user);

        // Call the method under test
        com.flashfuel.project.model.AddFuelQuoteResponse result = fuelQuoteService.addFuelQuote(request);

        // Verify interactions
        verify(userManager).getUserByUsername("testUser");
        verify(fuelQuoteManager).addFuelQuote(any(User.class), eq(500), eq("123 Fake St"), any(LocalDate.class), eq(2.5), eq(1250.0));

        // Assert results
        assertNotNull(result);
        assertEquals("Fuel quote added successfully.", result.getMessage());
    }
}

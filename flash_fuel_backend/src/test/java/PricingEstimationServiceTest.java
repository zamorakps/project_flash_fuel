
import com.flashfuel.project.ProjectApplication;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flashfuel.project.model.PricingEstimation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.PricingEstimationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
public class PricingEstimationServiceTest {

    @MockBean
    private FuelQuoteService fuelQuoteService;

    private PricingEstimationService pricingEstimationService;

    @BeforeEach
    public void setup() {
        pricingEstimationService = new PricingEstimationService(fuelQuoteService);
    }

    @Test
    public void calculatePricesTest() {
        // given
        String gallonsRequestedStr = "500";
        String state = "TX";
        Long userId = 1L;
        List<FuelQuote> quotes = new ArrayList<>();

        when(fuelQuoteService.getFuelQuotesByUserId(userId)).thenReturn(quotes);

        // when
        PricingEstimation result = pricingEstimationService.calculatePrices(gallonsRequestedStr, state, userId);

        // then
        assertNotNull(result);
        assertTrue(result.getSuggestedPricePerGallon() > 0);
        assertTrue(result.getTotalPrice() > 0);
    }
}

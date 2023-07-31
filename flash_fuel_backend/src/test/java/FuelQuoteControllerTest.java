import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.PricingEstimationService;
import com.flashfuel.project.model.ClientInformation;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.FuelQuoteDTO;
import com.flashfuel.project.model.PricingEstimation;
import com.flashfuel.project.ProjectApplication;
import com.flashfuel.project.config.TokenProvider;
import com.flashfuel.project.controller.FuelQuoteController;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProjectApplication.class)
public class FuelQuoteControllerTest {

    @Autowired
    private FuelQuoteController fuelQuoteController;

    @MockBean
    private FuelQuoteService fuelQuoteService;

    @MockBean
    private ClientInformationService clientInformationService;

    @MockBean
    private PricingEstimationService pricingEstimationService;

    @Autowired
    private TokenProvider tokenProvider;

    private String token;

    @BeforeEach
    public void setUp() {
        token = tokenProvider.generateToken("testUsername", 1L);
    }

    @Test
    public void testCalculatePrices() {
        // given
        String authHeader = "Bearer " + token;
        String gallonsRequested = "500";
        String clientState = "TX";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("gallonsRequested", gallonsRequested);
        requestBody.put("clientState", clientState);
        PricingEstimation pricingEstimation = new PricingEstimation();

        when(pricingEstimationService.calculatePrices(anyString(), anyString(), anyLong())).thenReturn(pricingEstimation);

        // when
        var result = fuelQuoteController.calculatePrices(authHeader, requestBody);

        // then
        verify(pricingEstimationService, times(1)).calculatePrices(anyString(), anyString(), anyLong());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(pricingEstimation, result.getBody());
    }

    @Test
    public void testAddFuelQuote() {
        // given
        String authHeader = "Bearer " + token;
        FuelQuoteDTO fuelQuoteDTO = new FuelQuoteDTO();

        doNothing().when(fuelQuoteService).addFuelQuote(anyLong(), any(FuelQuoteDTO.class));

        // when
        var result = fuelQuoteController.addFuelQuote(authHeader, fuelQuoteDTO);

        // then
        verify(fuelQuoteService, times(1)).addFuelQuote(anyLong(), any(FuelQuoteDTO.class));
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    public void testGetFuelQuoteHistory() {
        // given
        String authHeader = "Bearer " + token;
        List<FuelQuote> fuelQuoteList = new ArrayList<>();

        when(fuelQuoteService.getFuelQuotesByUserId(anyLong())).thenReturn(fuelQuoteList);

        // when
        var result = fuelQuoteController.getFuelQuoteHistory(authHeader);

        // then
        verify(fuelQuoteService, times(1)).getFuelQuotesByUserId(anyLong());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(fuelQuoteList, result.getBody());
    }

    @Test
    public void testGetUserProfile() {
        // given
        String authHeader = "Bearer " + token;
        ClientInformation clientInformation = new ClientInformation();

        when(clientInformationService.getClientInformationByUserId(anyLong())).thenReturn(clientInformation);

        // when
        var result = fuelQuoteController.getUserProfile(authHeader);

        // then
        verify(clientInformationService, times(1)).getClientInformationByUserId(anyLong());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(clientInformation, result.getBody());
    }
}

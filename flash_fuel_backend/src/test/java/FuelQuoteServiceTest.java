// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.flashfuel.project.FuelQuoteManager;
// import com.flashfuel.project.UserManager;
// import com.flashfuel.project.model.ClientInformation;
// import com.flashfuel.project.model.FuelQuote;
// import com.flashfuel.project.model.FuelQuoteDTO;
// import com.flashfuel.project.model.UserCredentials;
// import com.flashfuel.project.service.ClientInformationService;
// import com.flashfuel.project.service.FuelQuoteService;

// import java.util.Collections;

// import java.util.Arrays;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// import java.time.LocalDate;
// import java.util.List;

// @ExtendWith(MockitoExtension.class)
// public class FuelQuoteServiceTest {

//     @InjectMocks
//     private FuelQuoteService fuelQuoteService;

//     @Mock
//     private FuelQuoteManager fuelQuoteManager;

//     @Mock
//     private UserManager userManager;

//     @Mock
//     private ClientInformationService clientInformationService;

//     @BeforeEach
//     public void setup() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void getFuelQuotesByUserIdTest() {
//         // given
//         Long userId = 1L;
//         FuelQuote fuelQuote = new FuelQuote();
        
//         when(fuelQuoteManager.getFuelQuotesByUserId(userId)).thenReturn(Collections.singletonList(fuelQuote));

//         // when
//         var found = fuelQuoteService.getFuelQuotesByUserId(userId);

//         // then
//         assertFalse(found.isEmpty());
//         assertEquals(fuelQuote, found.get(0));
//     }

//     @Test
//     public void addFuelQuoteTest() {
//         // given
//         Long userId = 1L;
//         FuelQuoteDTO fuelQuoteDto = new FuelQuoteDTO();
//         fuelQuoteDto.setGallonsRequested(500);
//         fuelQuoteDto.setDeliveryDate(LocalDate.now());
//         fuelQuoteDto.setSuggestedPrice(2.0);
//         fuelQuoteDto.setTotalAmountDue(1000.0);

//         UserCredentials user = new UserCredentials();
//         user.setId(userId);
//         ClientInformation clientInformation = new ClientInformation();

//         when(userManager.getUserById(userId)).thenReturn(user);
//         when(clientInformationService.getClientInformationByUserId(userId)).thenReturn(clientInformation);
//         doNothing().when(fuelQuoteManager).addFuelQuote(user, any());

//         // when
//         fuelQuoteService.addFuelQuote(userId, fuelQuoteDto);

//         // then
//         verify(fuelQuoteManager, times(1)).addFuelQuote(user, any());
//     }
// }
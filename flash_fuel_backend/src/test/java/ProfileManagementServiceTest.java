/*
import com.flashfuel.project.model.UserProfileRequest;
import com.flashfuel.project.service.ProfileManagementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileManagementServiceTest {
    @Test
    void testUserShouldReturnFromGetProfile() {
        var service = new ProfileManagementService();

        assertEquals(
                "Test Username",
                service.getProfile("test").getUsername()
        );
    }

    @Test
    void updateWithoutNameShouldReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setCity("Houston");
        userProfileRequest.setZipCode("77003");
        userProfileRequest.setAddress("123 Fake St");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");
        userProfileRequest.setState("TX");

        assertTrue(
          service.updateProfile(userProfileRequest).toLowerCase().contains("name is required.")
        );
    }

    @Test
    void updateWithoutAddressShouldReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setName("First Last");
        userProfileRequest.setCity("Houston");
        userProfileRequest.setZipCode("77003");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");
        userProfileRequest.setState("TX");

        assertTrue(
                service.updateProfile(userProfileRequest).toLowerCase().contains("address is required.")
        );
    }

    @Test
    void updateWithoutCityShouldReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setName("First Last");
        userProfileRequest.setZipCode("77003");
        userProfileRequest.setAddress("123 Fake St");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");
        userProfileRequest.setState("TX");

        assertTrue(
                service.updateProfile(userProfileRequest).toLowerCase().contains("city is required.")
        );
    }

    @Test
    void updateWithoutStateShouldReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setName("First Last");
        userProfileRequest.setCity("Houston");
        userProfileRequest.setZipCode("77003");
        userProfileRequest.setAddress("123 Fake St");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");

        assertTrue(
                service.updateProfile(userProfileRequest).toLowerCase().contains("state is required.")
        );
    }

    @Test
    void updateWithoutZipCodeShouldReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setName("First Last");
        userProfileRequest.setCity("Houston");
        userProfileRequest.setAddress("123 Fake St");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");
        userProfileRequest.setState("TX");

        assertTrue(
                service.updateProfile(userProfileRequest).toLowerCase().contains("zip code is required.")
        );
    }

    @Test
    void updateWithInvalidZipCodeShouldReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setName("First Last");
        userProfileRequest.setCity("Houston");
        userProfileRequest.setZipCode("6546546");
        userProfileRequest.setAddress("123 Fake St");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");
        userProfileRequest.setState("TX");

        assertTrue(
                service.updateProfile(userProfileRequest).toLowerCase().contains("invalid zip code format.")
        );
    }

    @Test
    void updateWithValidRequestShouldNotReturnValidationMessage() {
        var service = new ProfileManagementService();
        var userProfileRequest = new UserProfileRequest();

        userProfileRequest.setName("First Last");
        userProfileRequest.setCity("Houston");
        userProfileRequest.setZipCode("77003");
        userProfileRequest.setAddress("123 Fake St");
        userProfileRequest.setUsername("TestUser");
        userProfileRequest.setAddressLine2("Apt 123");
        userProfileRequest.setState("TX");

        assertNull(service.updateProfile(userProfileRequest));
    }
}
 */
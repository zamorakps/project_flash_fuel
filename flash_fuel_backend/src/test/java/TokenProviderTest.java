import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashfuel.project.config.TokenProvider;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenProviderTest {

    private TokenProvider tokenProvider;
    private String validToken;
    private String tokenWithoutSubClaim;
    private String tokenWithoutIdClaim;

    private String SECRET_KEY = "rafey123";
    private long TOKEN_VALIDITY_DURATION = 3600000; // 1 hour

    @BeforeEach
    public void setup() {
        tokenProvider = new TokenProvider();
        validToken = tokenProvider.generateToken("test", 1);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY_DURATION);

        // Create a token without sub claim
        Map<String, Object> claimsWithoutSub = new HashMap<>();
        claimsWithoutSub.put("exp", expiryDate.getTime());
        claimsWithoutSub.put("id", 1);

        // Create a token without id claim
        Map<String, Object> claimsWithoutId = new HashMap<>();
        claimsWithoutId.put("sub", "test");
        claimsWithoutId.put("exp", expiryDate.getTime());

        tokenWithoutSubClaim = generateToken(claimsWithoutSub);
        tokenWithoutIdClaim = generateToken(claimsWithoutId);
    }

    private String generateToken(Map<String, Object> claims) {
        String encodedHeaderAndClaims = base64UrlEncode(claims);
        String signature = tokenProvider.generateHmacSHA256Signature(encodedHeaderAndClaims, SECRET_KEY);

        return encodedHeaderAndClaims + "." + signature;
    }

    private String base64UrlEncode(Map<String, Object> data) {
        String jsonData = tokenProvider.mapToJsonString(data);
        byte[] bytes = jsonData.getBytes(StandardCharsets.UTF_8);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Test
    public void testGetUsernameFromValidToken() {
        assertEquals("test", tokenProvider.getUsernameFromToken(validToken));
    }

    @Test
    public void testGetUsernameWithoutSubClaim() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tokenProvider.getUsernameFromToken(tokenWithoutSubClaim);
        });
        assertEquals("No 'sub' claim found in token", exception.getMessage());
    }

    @Test
    public void testGetIdFromValidToken() {
        assertEquals(1, tokenProvider.getIdFromToken(validToken));
    }

    @Test
    public void testGetIdWithoutIdClaim() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tokenProvider.getIdFromToken(tokenWithoutIdClaim);
        });
        assertEquals("No 'id' claim found in token", exception.getMessage());
    }
}

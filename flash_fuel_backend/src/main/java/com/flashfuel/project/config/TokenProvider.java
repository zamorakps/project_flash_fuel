package com.flashfuel.project.config;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class TokenProvider {

    private static final String SECRET_KEY = "rafey123";
    private static final long TOKEN_VALIDITY_DURATION = 3600000; // 1 hour

    public String generateToken(String username, long id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY_DURATION);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("exp", expiryDate.getTime());
        claims.put("id", id);

        String encodedHeaderAndClaims = base64UrlEncode(claims);
        String signature = generateHmacSHA256Signature(encodedHeaderAndClaims, SECRET_KEY);

        return encodedHeaderAndClaims + "." + signature;
    }

    private String base64UrlEncode(Map<String, Object> data) {
        String jsonData = mapToJsonString(data);
        byte[] bytes = jsonData.getBytes(StandardCharsets.UTF_8);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String mapToJsonString(Map<String, Object> data) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!first) {
                jsonString.append(",");
            } else {
                first = false;
            }
            jsonString.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
        }
        jsonString.append("}");
        return jsonString.toString();
    }

    private String generateHmacSHA256Signature(String data, String secret) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(secretKey);

            byte[] bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        } catch (NoSuchAlgorithmException | java.security.InvalidKeyException e) {
            throw new RuntimeException("Error generating HMAC-SHA256 signature", e);
        }
    }

    public boolean isValidToken(String token) {

        String[] tokenParts = token.split("\\.");
        if (tokenParts.length != 2) {
            throw new RuntimeException("Invalid token format");
        }

        String encodedClaims = tokenParts[0];
        String signature = tokenParts[1];

        String calculatedSignature = generateHmacSHA256Signature(encodedClaims, SECRET_KEY);
        if (!signature.equals(calculatedSignature)) {
            throw new RuntimeException("Invalid signature");
        }

        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedClaims);
        String decodedToken = new String(decodedBytes, StandardCharsets.UTF_8);

        System.out.println(decodedToken);
        int startOfExpClaim = decodedToken.indexOf("\"exp\":\"");
        if (startOfExpClaim == -1) {
            throw new RuntimeException("No 'exp' claim found in token");
        }
        startOfExpClaim += 7;

        int endOfExpClaim = decodedToken.indexOf("\"", startOfExpClaim);
        if (endOfExpClaim == -1) {
            throw new RuntimeException("Invalid 'exp' claim format in token");
        }

        long expiryTimeMillis = Long.parseLong(decodedToken.substring(startOfExpClaim, endOfExpClaim));

        if (System.currentTimeMillis() > expiryTimeMillis) {
            throw new RuntimeException("Token has expired");
        }

        int startOfSubClaim = decodedToken.indexOf("\"sub\":\"");
        if (startOfSubClaim == -1) {
            throw new RuntimeException("No 'sub' claim found in token");
        }
        startOfSubClaim += 7;

        int endOfSubClaim = decodedToken.indexOf("\"", startOfSubClaim);
        if (endOfSubClaim == -1) {
            throw new RuntimeException("Invalid 'sub' claim format in token");
        }

        int startOfIdClaim = decodedToken.indexOf("\"id\":\"");
        if (startOfIdClaim == -1) {
            throw new RuntimeException("No 'id' claim found in token");
        }
        startOfIdClaim += 6;

        int endOfIdClaim = decodedToken.indexOf("\"", startOfIdClaim);
        if (endOfIdClaim == -1) {
            throw new RuntimeException("Invalid 'id' claim format in token");
        }

        return true;
    }

    public String getUsernameFromToken(String token) {
        if (isValidToken(token)) {
            String[] tokenParts = token.split("\\.");
            String encodedClaims = tokenParts[0];
            byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedClaims);
            String decodedToken = new String(decodedBytes, StandardCharsets.UTF_8);

            int startOfSubClaim = decodedToken.indexOf("\"sub\":\"");
            startOfSubClaim += 7;
            int endOfSubClaim = decodedToken.indexOf("\"", startOfSubClaim);

            return decodedToken.substring(startOfSubClaim, endOfSubClaim);
        } else {
            return null;
        }
    }

    public long getIdFromToken(String token) {
        if (isValidToken(token)) {
            String[] tokenParts = token.split("\\.");
            String encodedClaims = tokenParts[0];
            byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedClaims);
            String decodedToken = new String(decodedBytes, StandardCharsets.UTF_8);

            int startOfIdClaim = decodedToken.indexOf("\"id\":\"");
            startOfIdClaim += 6;

            int endOfIdClaim = decodedToken.indexOf("\"", startOfIdClaim);
            return Integer.parseInt(decodedToken.substring(startOfIdClaim, endOfIdClaim));
        } else {
            return 0;
        }
    }
}
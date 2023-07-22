package com.flashfuel.project.config;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class TokenProvider {

    private static final String SECRET_KEY = "rafey123"; // Replace with a secure secret key
    private static final long TOKEN_VALIDITY_DURATION = 3600000; // 1 hour

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY_DURATION);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("exp", expiryDate.getTime());

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

    public String getUsernameFromToken(String token) {
        try {
            String[] tokenParts = token.split("\\.");
            if (tokenParts.length != 2) {
                throw new RuntimeException("Invalid token format");
            }

            byte[] decodedBytes = Base64.getUrlDecoder().decode(tokenParts[0]);
            String decodedToken = new String(decodedBytes);

            int startOfExpClaim = decodedToken.indexOf("\"exp\":");
            if (startOfExpClaim == -1) {
                throw new RuntimeException("No 'exp' claim found in token");
            }
            startOfExpClaim += 6;

            int endOfExpClaim = decodedToken.indexOf("}", startOfExpClaim);
            System.out.println(endOfExpClaim);
            if (endOfExpClaim == -1) {
                throw new RuntimeException("Invalid 'exp' claim format in token");
            }

            long expiryTimeMillis = Long.parseLong(decodedToken.substring(startOfExpClaim, endOfExpClaim - 1));
            System.out.println(expiryTimeMillis);
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

            return decodedToken.substring(startOfSubClaim, endOfSubClaim);
        } catch (Exception e) {
            return null;
        }
    }

}
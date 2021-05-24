package com.pj.CowinVaccineTracker.service;

import com.pj.CowinVaccineTracker.dto.*;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.pj.CowinVaccineTracker.helper.Restheaders.getRequestHeaders;

@Component
public class OTPService {

    @Value( "${cowinapi.generateOtpUrl}" )
    private String generateOtpUrl;
    @Value( "${cowinapi.validateOtpUrl}" )
    private String validateOtpUrl;

    private static Log log = LogFactory.getLog(OTPService.class);
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT = "%-20s:%s";
    private static String secret = "U2FsdGVkX18vDwDor+oOIG7vSUnINtlc/pxQcNiBulCm8LT5Sza+aIISKLqImbpMnRYgsN2QACPhggLWgZEpQg==";

    private Map<String, String> otpTranMap = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    public OTPResponse generateOTP(String mobileNumber){
        HttpEntity<OTPRequest> requestEntity = new HttpEntity<>(new OTPRequest(mobileNumber,secret), getRequestHeaders());
        OTPResponse otpResponse = restTemplate.postForEntity(generateOtpUrl, requestEntity, OTPResponse.class).getBody();
        otpTranMap.put(mobileNumber, otpResponse.getTxnId());
        return otpResponse;
    }



    public OTPVerificationResponse verifyOTP(String mobileNumber, String otp) {
        OTPVerificationRequest request = new OTPVerificationRequest(generateSha256(otp), otpTranMap.get(mobileNumber));
        HttpEntity<OTPVerificationRequest> requestEntity = new HttpEntity<>(request,  getRequestHeaders());

        return restTemplate.postForEntity(validateOtpUrl, requestEntity, OTPVerificationResponse.class).getBody();
    }

    public String generateSha256(String otp){

        String algorithm = "SHA-256";
        byte[] shaInBytes = digest(otp.getBytes(UTF_8), algorithm);
        return bytesToHex(shaInBytes);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] digest(byte[] input, String algorithm) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

}

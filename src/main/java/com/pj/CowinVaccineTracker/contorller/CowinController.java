package com.pj.CowinVaccineTracker.contorller;

import com.pj.CowinVaccineTracker.dto.CaptchaResponse;
import com.pj.CowinVaccineTracker.dto.OTPResponse;
import com.pj.CowinVaccineTracker.dto.OTPVerificationResponse;
import com.pj.CowinVaccineTracker.service.ImageService;
import com.pj.CowinVaccineTracker.service.OTPService;
import com.pj.CowinVaccineTracker.service.SchedulingService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CowinController {

    private static Log log = LogFactory.getLog(CowinController.class);

    @Autowired
    private OTPService otpService;

    @Autowired
    private SchedulingService schedulingService;
    @Autowired
    private ImageService imageService;

    Map<String, String> authTokenMap = new HashMap<>();

    //=========================STEP-1 ======================///

    @GetMapping("/app/getOTP/{mobileNumber}")
    public OTPResponse getOTP(@PathVariable String mobileNumber){
        log.info("Getting OTP for mobile: "+ mobileNumber);
        return otpService.generateOTP(mobileNumber);
    }

    @PostMapping("/app/verifyOTP/")
    public OTPVerificationResponse verifyOTP(@RequestParam("mobile") String mobile,
                                             @RequestParam("otp") String otp){
        log.info("Verifying OTP for mobile: "+ mobile);
        OTPVerificationResponse otpVerificationResponse = otpService.verifyOTP(mobile, otp);
        String token = otpVerificationResponse.getToken();
        log.info(mobile+ " :: " +token);
        authTokenMap.put(mobile, otpVerificationResponse.getToken());
        return otpVerificationResponse;
    }

    //=========================STEP-2======================///

    @GetMapping("/app/getcaptcha/{mobileNumber}")
    public String getCaptchaStringforUser(@PathVariable String mobileNumber){
        log.info("Request for captcha: "+ mobileNumber);
        return imageService.getCaptcha(authTokenMap.get(mobileNumber));
    }

    @PostMapping("/app/submitCaptcha/")
    public String submitCaptchaForUser(@RequestParam("captcha") String captcha,
                                       @RequestParam("mobileNumber") String mobileNumber){
        log.info("submitCaptchaForUser for captcha: " +captcha +" :: " + mobileNumber);
        return schedulingService.schedulewithCaptcha(null, captcha, authTokenMap.get(mobileNumber));
    }



}

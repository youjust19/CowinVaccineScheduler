package com.pj.CowinVaccineTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResponse {
    private String captcha;
    private String mobileNumber;
}

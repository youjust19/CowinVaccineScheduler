package com.pj.CowinVaccineTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    private int dose;
    private int center_id;
    private String session_id;
    private String slot;
    private List beneficiaries;
    private String captcha;
}

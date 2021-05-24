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
public class Session {
    public int center_id;
    public String name;
    public String address;
    public String state_name;
    public String district_name;
    public String block_name;
    public int pincode;
    public String from;
    public String to;
    public String fee_type;
    public String session_id;
    public String date;
    public int available_capacity_dose1;
    public int available_capacity_dose2;
    public int available_capacity;
    public String fee;
    public int min_age_limit;
    public String vaccine;
    public List<String> slots;
}

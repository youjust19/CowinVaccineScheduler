package com.pj.CowinVaccineTracker;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.pj.CowinVaccineTracker.schedulers.CheckVaccineAvailabilityFor18;
import com.pj.CowinVaccineTracker.service.OTPService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

//@SpringBootTest
public class CheckVaccineAvailabilityFor18Test {

    CheckVaccineAvailabilityFor18 service = new CheckVaccineAvailabilityFor18();

    @Test
    public void testCheckForAvailability(){
        service.checkForAvailability();
    }

    @Test
    public void testDateFormat(){
        int days = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now().plusDays(days);
        System.out.println(dtf.format(now));
    }

    @Test
    public void testForMatch(){
        DocumentContext parsedBody = JsonPath.parse(getResponseBody());
        Filter filter = filter(where("min_age_limit").is(45).and("available_capacity_dose2 ").gt(0D));
        List availableList = parsedBody.read("$.sessions[?]", List.class, filter);
        System.out.println(availableList.size());
    }

    @Test
    public void testSha256(){
        OTPService otpService = new OTPService();
        System.out.println(otpService.generateSha256("123432"));
    }


    String getResponseBody(){
        return "{\n" +
                "\t\"sessions\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 559097,\n" +
                "\t\t\t\"name\": \"C V Raman Hospital Block 2\",\n" +
                "\t\t\t\"address\": \"4th B Cross Rd Kalyan Nagar\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"East\",\n" +
                "\t\t\t\"pincode\": 560008,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"18:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"b0bf73da-d31e-4c83-88ae-f7ee8e7d5910\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 50,\n" +
                "\t\t\t\"available_capacity\": 50,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-03:00PM\",\n" +
                "\t\t\t\t\"03:00PM-06:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 517615,\n" +
                "\t\t\t\"name\": \"Matthikere UPHC\",\n" +
                "\t\t\t\"address\": \"Opp. Keshav Theater B.K Nagara\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"West\",\n" +
                "\t\t\t\"pincode\": 560022,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"18:00:00\",\n" +
                "\t\t\t\"lat\": 13,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"8f8d1d23-7eb0-425e-b896-70779f285052\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 10,\n" +
                "\t\t\t\"available_capacity\": 10,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-03:00PM\",\n" +
                "\t\t\t\t\"03:00PM-06:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 407206,\n" +
                "\t\t\t\"name\": \"Varthuru UPHC P3\",\n" +
                "\t\t\t\"address\": \"Varthuru Main Road Near Varthuru Police Station\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Mahadevapura\",\n" +
                "\t\t\t\"pincode\": 560087,\n" +
                "\t\t\t\"from\": \"10:00:00\",\n" +
                "\t\t\t\"to\": \"16:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"61c3e324-b9c0-4d5a-8c73-759e33b186a0\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 13,\n" +
                "\t\t\t\"available_capacity_dose2\": 0,\n" +
                "\t\t\t\"available_capacity\": 13,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-04:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 407039,\n" +
                "\t\t\t\"name\": \"Laggere UPHC\",\n" +
                "\t\t\t\"address\": \"Muneshwara Layout\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Dasarahalli\",\n" +
                "\t\t\t\"pincode\": 560058,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"18:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"83778e19-e061-4974-b908-bbc84398c730\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 50,\n" +
                "\t\t\t\"available_capacity\": 50,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-03:00PM\",\n" +
                "\t\t\t\t\"03:00PM-06:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 561106,\n" +
                "\t\t\t\"name\": \"YAMLURU UPHC P3\",\n" +
                "\t\t\t\"address\": \"Kempapura MAIN ROAD,Bellandur, Bengaluru\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Mahadevapura\",\n" +
                "\t\t\t\"pincode\": 560037,\n" +
                "\t\t\t\"from\": \"10:00:00\",\n" +
                "\t\t\t\"to\": \"16:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"df9cb831-00b1-434e-a019-9eb259410abc\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 3,\n" +
                "\t\t\t\"available_capacity_dose2\": 0,\n" +
                "\t\t\t\"available_capacity\": 3,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-04:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 407217,\n" +
                "\t\t\t\"name\": \"Vibuthipura UPHC P3\",\n" +
                "\t\t\t\"address\": \"Annasandrapalya Main Rd, Ramesh Nagar, Vibhutipura, Bengaluru,\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Mahadevapura\",\n" +
                "\t\t\t\"pincode\": 560037,\n" +
                "\t\t\t\"from\": \"10:00:00\",\n" +
                "\t\t\t\"to\": \"16:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"02e2d490-1bfc-4668-9b66-58a6a451495d\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 10,\n" +
                "\t\t\t\"available_capacity_dose2\": 0,\n" +
                "\t\t\t\"available_capacity\": 10,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-04:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 596452,\n" +
                "\t\t\t\"name\": \"Vanivillas Hospital 1\",\n" +
                "\t\t\t\"address\": \"VICTORIA\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"West\",\n" +
                "\t\t\t\"pincode\": 560018,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"13:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"f7cac27b-1d4c-43ba-a889-7238218636a3\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 25,\n" +
                "\t\t\t\"available_capacity\": 25,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-10:00AM\",\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 565262,\n" +
                "\t\t\t\"name\": \"HEROHALLI UPHC\",\n" +
                "\t\t\t\"address\": \"Magadi Main Road\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Dasarahalli\",\n" +
                "\t\t\t\"pincode\": 560091,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"18:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"226ce5ec-6e2b-45c5-af52-5ac006a00d64\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 5,\n" +
                "\t\t\t\"available_capacity\": 5,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-03:00PM\",\n" +
                "\t\t\t\t\"03:00PM-06:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 558441,\n" +
                "\t\t\t\"name\": \"Bowring Block 1A\",\n" +
                "\t\t\t\"address\": \"Lady Curzon Rd Shivaji Nagar\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"East\",\n" +
                "\t\t\t\"pincode\": 560051,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"18:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"23c2fd42-b344-4f4a-9463-3f3897416926\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 50,\n" +
                "\t\t\t\"available_capacity\": 50,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-03:00PM\",\n" +
                "\t\t\t\t\"03:00PM-06:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 686670,\n" +
                "\t\t\t\"name\": \"THIPPENAHALLI PHC COVISHIELD\",\n" +
                "\t\t\t\"address\": \"THIPPENAHALLI DODDABIDARAKKALU\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Dasarahalli\",\n" +
                "\t\t\t\"pincode\": 560057,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"18:00:00\",\n" +
                "\t\t\t\"lat\": 13,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"381131df-057d-420b-8b60-0be7f2868379\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 1,\n" +
                "\t\t\t\"available_capacity\": 1,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-03:00PM\",\n" +
                "\t\t\t\t\"03:00PM-06:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 679403,\n" +
                "\t\t\t\"name\": \"ITI HOSPITAL P3\",\n" +
                "\t\t\t\"address\": \"Duravani Nagar Dooravani Nagar Bengaluru Karnataka 560016\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Mahadevapura\",\n" +
                "\t\t\t\"pincode\": 560016,\n" +
                "\t\t\t\"from\": \"10:00:00\",\n" +
                "\t\t\t\"to\": \"16:00:00\",\n" +
                "\t\t\t\"lat\": 13,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"ce14866e-96c4-452a-94f1-24a5fad1eabb\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 1,\n" +
                "\t\t\t\"available_capacity_dose2\": 0,\n" +
                "\t\t\t\"available_capacity\": 1,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-04:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 701691,\n" +
                "\t\t\t\"name\": \"Yelahanka KODIGEHALLI UPHC\",\n" +
                "\t\t\t\"address\": \"Kodigehalli Main Road\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"Yelahanka\",\n" +
                "\t\t\t\"pincode\": 560092,\n" +
                "\t\t\t\"from\": \"10:00:00\",\n" +
                "\t\t\t\"to\": \"16:00:00\",\n" +
                "\t\t\t\"lat\": 13,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"8c57b90e-155e-44f7-8a48-60a51508aa94\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 50,\n" +
                "\t\t\t\"available_capacity\": 50,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\",\n" +
                "\t\t\t\t\"01:00PM-04:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 604573,\n" +
                "\t\t\t\"name\": \"VICTORIA\",\n" +
                "\t\t\t\"address\": \"VICTORIA HOSPITAL CAMPUS K R MARKET\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"West\",\n" +
                "\t\t\t\"pincode\": 560002,\n" +
                "\t\t\t\"from\": \"09:00:00\",\n" +
                "\t\t\t\"to\": \"13:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"c8ce1564-7a48-427b-9923-b8e4a0c7e6d4\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 25,\n" +
                "\t\t\t\"available_capacity\": 25,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"09:00AM-10:00AM\",\n" +
                "\t\t\t\t\"10:00AM-11:00AM\",\n" +
                "\t\t\t\t\"11:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-01:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"center_id\": 604420,\n" +
                "\t\t\t\"name\": \"K C Gen Hospital\",\n" +
                "\t\t\t\"address\": \"Malleshwarm Circle Bengaluru\",\n" +
                "\t\t\t\"state_name\": \"Karnataka\",\n" +
                "\t\t\t\"district_name\": \"BBMP\",\n" +
                "\t\t\t\"block_name\": \"West\",\n" +
                "\t\t\t\"pincode\": 560003,\n" +
                "\t\t\t\"from\": \"10:00:00\",\n" +
                "\t\t\t\"to\": \"17:00:00\",\n" +
                "\t\t\t\"lat\": 12,\n" +
                "\t\t\t\"long\": 77,\n" +
                "\t\t\t\"fee_type\": \"Free\",\n" +
                "\t\t\t\"session_id\": \"c4feddc0-3a48-4ac9-a42b-5219b0cfd8ef\",\n" +
                "\t\t\t\"date\": \"20-05-2021\",\n" +
                "\t\t\t\"available_capacity_dose1\": 0,\n" +
                "\t\t\t\"available_capacity_dose2\": 25,\n" +
                "\t\t\t\"available_capacity\": 25,\n" +
                "\t\t\t\"fee\": \"0\",\n" +
                "\t\t\t\"min_age_limit\": 45,\n" +
                "\t\t\t\"vaccine\": \"COVISHIELD\",\n" +
                "\t\t\t\"slots\": [\n" +
                "\t\t\t\t\"10:00AM-12:00PM\",\n" +
                "\t\t\t\t\"12:00PM-02:00PM\",\n" +
                "\t\t\t\t\"02:00PM-04:00PM\",\n" +
                "\t\t\t\t\"04:00PM-05:00PM\"\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}\n" +
                "\n";
    }
}

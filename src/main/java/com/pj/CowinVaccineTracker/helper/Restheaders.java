package com.pj.CowinVaccineTracker.helper;

import org.springframework.http.HttpHeaders;

import java.util.List;

public class Restheaders {

    public static HttpHeaders getRequestHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("User-Agent", List.of("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"));
        httpHeaders.set("authorization","Bearer "+token);
        return httpHeaders;
    }

    public static HttpHeaders getRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("User-Agent", List.of("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"));
        return httpHeaders;
    }
}

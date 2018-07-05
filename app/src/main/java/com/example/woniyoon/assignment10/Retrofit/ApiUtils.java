package com.example.woniyoon.assignment10.Retrofit;

public class ApiUtils {
//    public static final String BASE_URL = "http://woniyoon.ddns.net/webapi/interview/";

    public static final String BASE_URL = "http://10.0.2.2:8080/backend/webapi/interview/";

    public static RetrofitService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitService.class);
    }
}
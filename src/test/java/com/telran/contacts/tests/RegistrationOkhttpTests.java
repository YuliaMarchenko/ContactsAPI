package com.telran.contacts.tests;

import com.google.gson.Gson;
import com.telran.contacts.dto.AuthRequestDto;
import com.telran.contacts.dto.ErrorDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.telran.contacts.tests.ContactOkHttpTests.JSON;

public class RegistrationOkhttpTests {
    @Test
    public void registrationNegativeTestWithInvalidPassword() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test109@test.com")
                .password("test12TEST").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/registration")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        ErrorDto errorDto = gson.fromJson(responseJson, ErrorDto.class);

        System.out.println(errorDto.getCode());
        System.out.println(errorDto.getMessage());
    }
}

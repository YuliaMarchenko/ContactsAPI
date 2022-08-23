package com.telran.contacts.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.telran.contacts.dto.AuthRequestDto;
import com.telran.contacts.dto.ErrorDto;
import com.telran.contacts.dto.LoginRegResponseDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContactAPITests {
    @Test
    public void loginAPITest() throws IOException {
        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"test102@test.com\",\n" +
                        "  \"password\": \"test12_TEST\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        System.out.println(response);
        System.out.println("****************");
        String responseJson = response.returnContent().asString();
        System.out.println(responseJson);
        System.out.println("****************");
        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement token = element.getAsJsonObject().get("token");
        System.out.println(token.getAsString());
    }

    @Test
    public void loginHttpTest1() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test102@test.com")
                .password("test12_TEST").build();

        Gson gson = new Gson();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnContent().asString();

        LoginRegResponseDto regResponseDto = gson.fromJson(responseJson, LoginRegResponseDto.class);
        System.out.println(regResponseDto);
    }

    @Test
    public void loginHttpNegativeTest2WithInvalidPassword() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test102@test.com")
                .password("test12TEST").build();

        Gson gson = new Gson();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse);
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream is = httpResponse.getEntity().getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }

        ErrorDto errorDto = gson.fromJson(stringBuilder.toString(), ErrorDto.class);
        System.out.println(errorDto.getDetails());
        System.out.println(errorDto.getMessage());
    }
}

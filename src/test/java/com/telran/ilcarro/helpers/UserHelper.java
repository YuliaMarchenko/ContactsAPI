package com.telran.ilcarro.helpers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.telran.ilcarro.dto.RegistrationDto;

import static com.jayway.restassured.RestAssured.given;

public class UserHelper {
    public static final String EMAIL = "test1016@test.test";
    public static final String PASSWORD =  "testTEST121";

    public static Response deleteUser(String email, String password){
        return given().contentType("application/json")
                .header("Authorization", AuthHelper.basicAuth(email, password))
                .delete("user");
    }

    public static Response createUser(String email, String password){
            RegistrationDto requestDto = RegistrationDto.builder()
                    .first_name("Test")
                    .second_name("Testovich")
                    .build();

            return given().contentType("application/json")
                    .header("Authorization", AuthHelper.basicAuth(email, password))
                    .body(requestDto)
                    .post("registration");
    }
}

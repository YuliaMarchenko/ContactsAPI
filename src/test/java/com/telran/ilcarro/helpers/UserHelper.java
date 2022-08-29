package com.telran.ilcarro.helpers;

import com.jayway.restassured.response.Response;
import com.telran.ilcarro.dto.RegistrationDto;
import com.telran.ilcarro.dto.UserBaseDto;

import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;

public class UserHelper {
    private String email;
    private String password;

    public UserHelper() {
        this.email = "test" + System.currentTimeMillis() + "@test.test";
        this.password = "testTEST121";
    }

    public Response deleteUser(){
        return given().contentType("application/json")
                .header("Authorization", AuthHelper.basicAuth(email, password))
                .delete("user");
    }

    public Response createUser(){
            RegistrationDto requestDto = RegistrationDto.builder()
                    .first_name("Test")
                    .second_name("Testovich")
                    .build();

            return given().contentType("application/json")
                    .header("Authorization", AuthHelper.basicAuth(email, password))
                    .body(requestDto)
                    .post("registration");
    }

    public Response updateUser(){
        UserBaseDto userBaseDto = UserBaseDto.builder()
                .first_name("Test")
                .photo("gfhkggh")
                .second_name("Testovich")
                .build();

        return  given().contentType("application/json")
                .header("Authorization", AuthHelper.basicAuth(email, password))
                .header("X-New-Password", "Basic " + Base64.getEncoder().encodeToString("test1009@test.com:testTEST123".getBytes()))
                .body(userBaseDto)
                .put("user");
    }
}

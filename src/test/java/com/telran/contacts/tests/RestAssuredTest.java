package com.telran.contacts.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contacts.dto.AuthRequestDto;
import com.telran.contacts.dto.ContactDto;
import com.telran.contacts.dto.GetAllContactsDto;
import com.telran.contacts.dto.LoginRegResponseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
    @BeforeMethod
    public void ensurePrecondition(){
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";
    }

    @Test
    public void loginPositiveTest(){
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test102@test.com")
                .password("test12_TEST")
                .build();

        LoginRegResponseDto responseDto = given()
                .contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(LoginRegResponseDto.class);

        System.out.println(responseDto.getToken());

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3QxMDJAdGVzdC5jb20ifQ.Npf-xrwHZC21Ls2iIKzby8HeAxZTTN5u90-rXJc8O_w";

        String token2 = given().contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .body("token",equalTo(token))
                .extract().path("token");
        System.out.println(token2);
    }

    @Test
    public void loginNegativeTestWithInvalidPassword(){
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test102@test.com")
                .password("test12TEST")
                .build();

        String message = given().contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(400)
                .extract().path("message");
        System.out.println(message);
    }

    @Test
    public void addNewContactPositiveTest(){

        int i = (int)((System.currentTimeMillis()/1000)%3600);

        ContactDto contactDto = ContactDto.builder()
                .address("Berlin")
                .description("description")
                .email("test@test.test")
                .lastName("Sidorov")
                .name("Vasia")
                .phone("4567572809982" + i)
                .build();

        int id = given().header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3QxMDJAdGVzdC5jb20ifQ.Npf-xrwHZC21Ls2iIKzby8HeAxZTTN5u90-rXJc8O_w")
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");

        System.out.println(id);
    }

    @Test
    public void getAllContactsTest(){
        GetAllContactsDto responseDto = given()
                .header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3QxMDJAdGVzdC5jb20ifQ.Npf-xrwHZC21Ls2iIKzby8HeAxZTTN5u90-rXJc8O_w")
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(GetAllContactsDto.class);

        for(ContactDto contactDto: responseDto.getContacts()){
            System.out.println(contactDto.getId() + " " + contactDto.getLastName());
            System.out.println("********************");
        }
    }

    @Test
    public void deleteContactTest() {
    }
}

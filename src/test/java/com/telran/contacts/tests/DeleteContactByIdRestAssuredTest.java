package com.telran.contacts.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contacts.dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIdRestAssuredTest {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3QxMDJAdGVzdC5jb20ifQ.Npf-xrwHZC21Ls2iIKzby8HeAxZTTN5u90-rXJc8O_w";
    int id;

    @BeforeMethod
    public void ensurePrecondition(){
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

        int i = (int)((System.currentTimeMillis()/1000)%3600);

        ContactDto contactDto = ContactDto.builder()
                .address("Berlin")
                .description("description")
                .email("test@test.test")
                .lastName("Sidorov")
                .name("Vasia")
                .phone("4567572809982" + i)
                .build();

        id = given().header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3QxMDJAdGVzdC5jb20ifQ.Npf-xrwHZC21Ls2iIKzby8HeAxZTTN5u90-rXJc8O_w")
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");

        id = contactDto.getId();
    }

    @Test
    public void deleteByIdTest(){
        String status = given().header("Authorization", token)
                .delete("/contact/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);
    }
}

package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class LoginTests {
    @BeforeMethod
    public void ensurePrecondition(){
        RestAssured.baseURI = "java-3-ilcarro-team-b.herokuapp.com/";
        RestAssured.basePath = "user";
    }
}

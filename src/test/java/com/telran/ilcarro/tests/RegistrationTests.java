package com.telran.ilcarro.tests;

import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest{

    @Test
    public void registrationPositiveTest() {
       user.createUser()
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void registrationSecondTimeNegativeTest() {
        user.createUser()
                .then()
                .assertThat().statusCode(409);
    }

    @AfterSuite
    public void tearDown(){
        user.deleteUser();
    }
}
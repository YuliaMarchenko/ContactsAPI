package com.telran.ilcarro.tests;

import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest{

    @Test
    public void registrationPositiveTest() {
       UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void registrationSecondTimeNegativeTest() {
        UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD)
                .then()
                .assertThat().statusCode(409);
    }

    @AfterSuite
    public void tearDown(){
        UserHelper.deleteUser(UserHelper.EMAIL, UserHelper.PASSWORD);
    }
}
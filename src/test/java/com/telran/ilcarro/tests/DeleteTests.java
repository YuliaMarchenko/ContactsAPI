package com.telran.ilcarro.tests;

import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteTests extends BaseTest{
    @BeforeMethod
    public void ensurePrecondition() {
        UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD);
    }

    @Test
    public void deletePositiveTest() {
        UserHelper.deleteUser(UserHelper.EMAIL, UserHelper.PASSWORD)
                .then()
                .assertThat().statusCode(200);
    }
}

package com.telran.ilcarro.helpers;

import java.util.Base64;

public class AuthHelper {
    public static String basicAuth(String email, String password) {
        return "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", email, password).getBytes());
    }
}

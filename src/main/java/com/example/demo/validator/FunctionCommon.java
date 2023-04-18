package com.example.demo.validator;

import org.springframework.stereotype.Component;

@Component
public class FunctionCommon {

    public static boolean isValidEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@gmail.com";
        return email.matches(regex);
    }

}

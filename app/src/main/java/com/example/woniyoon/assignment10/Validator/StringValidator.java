package com.example.woniyoon.assignment10.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator implements Validator<String> {

    @Override
    public boolean isValid(String s, Pattern regex) {
        Matcher m = regex.matcher(s);

        if (m.find()) {
            return true;
        }
        return false;
    }
}

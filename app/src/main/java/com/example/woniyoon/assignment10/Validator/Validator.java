package com.example.woniyoon.assignment10.Validator;

import java.util.regex.Pattern;

public interface Validator<T> {

    boolean isValid(T t, Pattern regex);
}

package zw.co.bancabc.commonutils.domain.value;

import zw.co.firstmutual.ussdcommons.domain.enums.ExceptionCode;
import zw.co.firstmutual.ussdcommons.exceptions.InvalidEmailException;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Email(String value) implements Serializable {
    private static final String REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public Email {
        if (!isEmailValid(value)) {
            throw new InvalidEmailException("{invalid.email}", ExceptionCode.INVALID_EMAIL);
        }
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return value;
    }
}
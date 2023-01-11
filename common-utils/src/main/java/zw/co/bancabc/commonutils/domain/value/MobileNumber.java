package zw.co.bancabc.commonutils.domain.value;

import zw.co.firstmutual.ussdcommons.domain.enums.ExceptionCode;
import zw.co.firstmutual.ussdcommons.exceptions.MobileNumberInvalidException;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record MobileNumber(String value) implements Serializable {

    private static final String REGEX = "^[0-9]{3,14}$";

    public MobileNumber {
        if (!isValidPhoneNumber(value)) {
            throw new MobileNumberInvalidException("{invalid.phone.number}", ExceptionCode.INVALID_PHONE_NUMBER);
        }
    }

    public static boolean isValidPhoneNumber(final String number) {

        final Pattern pattern = Pattern.compile(REGEX);
        final Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return value;
    }
}
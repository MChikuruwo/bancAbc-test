package zw.co.bancabc.commonutils.domain.response;

import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.UserName;

import java.time.LocalDate;

public record UserResponse(String firstName, String lastName,
                           MobileNumber mobileNumber, UserName username, Email email) {
}
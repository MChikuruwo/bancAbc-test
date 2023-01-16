package zw.co.bancabc.commonutils.domain.response;

import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.UserName;

public record LogInResponse(String firstName, String lastName, UserName userName) {
}
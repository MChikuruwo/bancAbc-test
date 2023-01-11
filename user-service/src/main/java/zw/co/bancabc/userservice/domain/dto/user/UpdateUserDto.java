package zw.co.bancabc.userservice.domain.dto.user;

import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.UserName;

public record UpdateUserDto(String firstName, String lastName, Email email, UserName userName, String password) {

}
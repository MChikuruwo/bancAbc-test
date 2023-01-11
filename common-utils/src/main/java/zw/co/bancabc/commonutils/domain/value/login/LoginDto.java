package zw.co.bancabc.commonutils.domain.value.login;

import zw.co.bancabc.commonutils.domain.value.Email;

public record LoginDto(Email email, String pin) {
}

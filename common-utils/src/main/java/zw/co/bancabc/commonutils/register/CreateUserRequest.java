package zw.co.bancabc.commonutils.register;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.UserName;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private MobileNumber mobileNumber;

    @Valid
    private UserName userName;

    @Valid
    private Email email;

    @Valid
    private String password;

    public CreateUserRequest(String firstName, String lastName, UserName userName, Email email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
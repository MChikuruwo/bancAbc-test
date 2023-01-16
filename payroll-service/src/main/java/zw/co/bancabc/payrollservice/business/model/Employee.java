package zw.co.bancabc.payrollservice.business.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import zw.co.bancabc.commonutils.domain.converter.EmailConverter;
import zw.co.bancabc.commonutils.domain.converter.IdNumberConverter;
import zw.co.bancabc.commonutils.domain.converter.MobileNumberConverter;
import zw.co.bancabc.commonutils.domain.enums.EmploymentContract;
import zw.co.bancabc.commonutils.domain.enums.Gender;
import zw.co.bancabc.commonutils.domain.enums.MaritalStatus;
import zw.co.bancabc.commonutils.domain.enums.Title;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.login.IdNumber;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonDeserialize
@ToString
public class Employee extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;


    @Past(message = "date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Title title;

    @Convert(converter = IdNumberConverter.class)
    @Column(unique = true)
    private IdNumber idNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    @Convert(converter = MobileNumberConverter.class)
    private MobileNumber mobileNumber;

    private String physicalAddress;

    @Column(unique = true)
    @Convert(converter = EmailConverter.class)
    private Email email;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    private EmploymentContract contract;

    @Column(unique = true)
    private String employeeCode;

    private boolean isActive;
}

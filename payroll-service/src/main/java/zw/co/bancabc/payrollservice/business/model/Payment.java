package zw.co.bancabc.payrollservice.business.model;

import lombok.*;
import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Payment extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String employeeName;

    private String employeeCode;

    private BigInteger salaryAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(unique = true)
    private String paymentReference;

    private boolean isApproved;


    public Payment(long id, String employeeName, String employeeCode, BigInteger salaryAmount, PaymentStatus paymentStatus, String paymentReference) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeCode = employeeCode;
        this.salaryAmount = salaryAmount;
        this.paymentStatus = paymentStatus;
        this.paymentReference = paymentReference;
    }
}

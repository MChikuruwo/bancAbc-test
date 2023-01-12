package zw.co.bancabc.payrollservice.business.model;

import lombok.*;
import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Payment extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigInteger salaryAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private boolean isApproved;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Employee> employees;
}

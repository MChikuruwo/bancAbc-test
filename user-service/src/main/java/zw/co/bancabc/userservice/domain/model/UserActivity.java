package zw.co.bancabc.userservice.domain.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class UserActivity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long entityId;

    private String narrative;

    @ManyToOne
    private AuditTrail auditTrail;
}
package zw.co.bancabc.userservice.domain.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Login extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;
}
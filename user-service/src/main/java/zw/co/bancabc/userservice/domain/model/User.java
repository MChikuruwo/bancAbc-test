package zw.co.bancabc.userservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zw.co.bancabc.commonutils.domain.converter.EmailConverter;
import zw.co.bancabc.commonutils.domain.converter.MobileNumberConverter;
import zw.co.bancabc.commonutils.domain.converter.UserNameConverter;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.UserName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    @Convert(converter = MobileNumberConverter.class)
    private MobileNumber mobileNumber;

    @Column(unique = true)
    @Convert(converter = UserNameConverter.class)
    private UserName userName;

    @Column(unique = true)
    @Convert(converter = EmailConverter.class)
    private Email email;

    private String password;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> role;
}
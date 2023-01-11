package zw.co.bancabc.userservice.domain.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.repository.CustomRepository;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.UserName;
import zw.co.bancabc.userservice.domain.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomRepository<User, Long> {
    User findUserByMobileNumber(@Param("mobileNumber") MobileNumber mobileNumber);

    User findUserByUserName(@Param("userName") UserName userName);

    Optional<User> findUserByEmail(@Param("email") Email email);
}
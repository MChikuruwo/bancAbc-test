package zw.co.bancabc.userservice.domain.repository;

import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.repository.CustomRepository;
import zw.co.bancabc.userservice.domain.model.User;

@Repository
public interface UserRoleRepository extends CustomRepository<User, Long> {

}

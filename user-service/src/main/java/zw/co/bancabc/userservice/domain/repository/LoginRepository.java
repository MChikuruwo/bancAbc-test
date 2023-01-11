package zw.co.bancabc.userservice.domain.repository;

import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.repository.CustomRepository;
import zw.co.bancabc.userservice.domain.model.Login;
import zw.co.bancabc.userservice.domain.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface LoginRepository extends CustomRepository<Login, Long> {
    List<Login> findAllByUser(User user);

    List<Login> findAllByCreatedDate(Date date);
}

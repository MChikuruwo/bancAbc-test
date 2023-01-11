package zw.co.bancabc.userservice.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.repository.CustomRepository;
import zw.co.bancabc.userservice.domain.model.UserActivity;

import java.util.List;

@Repository
public interface ActivityRepository extends CustomRepository<UserActivity,Long> {

    @Query("SELECT a from UserActivity a WHERE a.entityId = ?1")
    List<UserActivity> getAllByEntityId(Long entityId);
}
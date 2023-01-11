package zw.co.bancabc.userservice.domain.repository;

import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.repository.CustomRepository;
import zw.co.bancabc.userservice.domain.model.AuditTrail;

import java.util.List;

@Repository
public interface AuditTrailRepository extends CustomRepository<AuditTrail, Long> {
    List<AuditTrail> findByUser(Long userId);
}
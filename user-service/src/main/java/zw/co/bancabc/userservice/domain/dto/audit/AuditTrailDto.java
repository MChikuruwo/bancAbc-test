package zw.co.bancabc.userservice.domain.dto.audit;

import zw.co.bancabc.userservice.domain.model.User;

public record AuditTrailDto(long id, User user) {
}
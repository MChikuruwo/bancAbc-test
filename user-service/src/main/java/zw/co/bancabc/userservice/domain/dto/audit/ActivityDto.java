package zw.co.bancabc.userservice.domain.dto.audit;

import zw.co.bancabc.userservice.domain.model.AuditTrail;

public record ActivityDto(Long id, Long entityId, String narrative, AuditTrail auditTrail) {

}
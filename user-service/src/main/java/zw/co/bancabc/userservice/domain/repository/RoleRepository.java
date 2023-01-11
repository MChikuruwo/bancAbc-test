package zw.co.bancabc.userservice.domain.repository;

import zw.co.bancabc.commonutils.domain.repository.CustomRepository;
import zw.co.bancabc.userservice.domain.model.Role;

public interface RoleRepository extends CustomRepository<Role, Long> {
    Role findByName(String name);
}
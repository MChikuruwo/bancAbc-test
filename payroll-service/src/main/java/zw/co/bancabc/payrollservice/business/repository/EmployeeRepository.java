package zw.co.bancabc.payrollservice.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.payrollservice.business.model.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findEmployeeByEmployeeCode(String employeeCode);

    Optional<Employee> findEmployeeByEmail(Email email);
}

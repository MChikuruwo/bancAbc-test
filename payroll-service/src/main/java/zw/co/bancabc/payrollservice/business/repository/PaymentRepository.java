package zw.co.bancabc.payrollservice.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.bancabc.payrollservice.business.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}

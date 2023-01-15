package zw.co.bancabc.payrollservice.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;
import zw.co.bancabc.payrollservice.business.model.Payment;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);

    Optional<Payment> findPaymentByPaymentReference(String paymentReference);


}

package zw.co.bancabc.payrollservice.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;
import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;
import zw.co.bancabc.commonutils.exceptions.CSVUploadException;
import zw.co.bancabc.commonutils.exceptions.PaymentReferenceNotFoundException;
import zw.co.bancabc.commonutils.exceptions.PaymentsUnavailableException;
import zw.co.bancabc.commonutils.util.OtherUtils;
import zw.co.bancabc.payrollservice.business.model.CSVHelper;
import zw.co.bancabc.payrollservice.business.model.Payment;
import zw.co.bancabc.payrollservice.business.repository.PaymentRepository;
import zw.co.bancabc.payrollservice.payload.request.PaymentRequest;
import zw.co.bancabc.payrollservice.payload.response.PaymentResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmployeeService employeeService;


    public PaymentResponse addIndividualPayment(String employeeCode, PaymentRequest paymentRequest) {

        var employee = employeeService.findEmployeeByEmployeeCode(employeeCode);

        var payment = Payment.builder()
                .employeeName(employee.getFirstName().concat(" ").concat(employee.getLastName()))
                .employeeCode(employee.getEmployeeCode())
                .paymentStatus(PaymentStatus.PENDING)
                .salaryAmount(paymentRequest.salaryAmount())
                .paymentReference(OtherUtils.reference())
                .isApproved(false)
                .build();

        paymentRepository.save(payment);

        return new PaymentResponse(payment.getPaymentReference(), payment.getEmployeeName(), payment.getEmployeeCode(), payment.getSalaryAmount(), payment.getPaymentStatus());
    }

    public PaymentResponse approvePayment(String reference) {

        var paymentToApprove = paymentRepository.findPaymentByPaymentReference(reference);

        if (paymentToApprove.isEmpty())
            throw new PaymentReferenceNotFoundException("reference: " + reference + " not found", ExceptionCode.PAYMENT_REFERENCE_NOT_FOUND);

//        var payment = modelMapper.map(paymentStatusUpdateRequest, Payment.class);

        paymentToApprove.get().setPaymentStatus(PaymentStatus.APPROVED);

        paymentToApprove.get().setApproved(true);

//        paymentToApprove.get().setCreatedDate(paymentToApprove.get().getCreatedDate());

        paymentRepository.save(paymentToApprove.get());

        return new PaymentResponse(paymentToApprove.get().getPaymentReference(), paymentToApprove.get().getEmployeeName(), paymentToApprove.get().getEmployeeCode(),
                paymentToApprove.get().getSalaryAmount(), paymentToApprove.get().getPaymentStatus());

    }

    public PaymentResponse rejectPayment(String reference) {
        var paymentToReject = paymentRepository.findPaymentByPaymentReference(reference);

        if (paymentToReject.isEmpty())
            throw new PaymentReferenceNotFoundException("reference: " + reference + " not found", ExceptionCode.PAYMENT_REFERENCE_NOT_FOUND);

        paymentToReject.get().setPaymentStatus(PaymentStatus.REJECTED);

//        paymentToReject.get().setCreatedDate(paymentToReject.get().getCreatedDate());

        paymentRepository.save(paymentToReject.get());

        return new PaymentResponse(paymentToReject.get().getPaymentReference(), paymentToReject.get().getEmployeeName(), paymentToReject.get().getEmployeeCode(),
                paymentToReject.get().getSalaryAmount(), paymentToReject.get().getPaymentStatus());
    }

    public List<PaymentResponse> findByPaymentsByStatus(String paymentStatus) {

        var payments = paymentRepository.findAllByPaymentStatus(PaymentStatus.valueOf(paymentStatus));

        if (payments.isEmpty())
            throw new PaymentsUnavailableException("payments for status: " + paymentStatus + " not found.", ExceptionCode.PAYMENTS_UNAVAILABLE);

        return payments.stream().map(a -> new PaymentResponse(a.getPaymentReference(), a.getEmployeeName(), a.getEmployeeCode(), a.getSalaryAmount(), a.getPaymentStatus())).toList();

    }

    public void bulkUploadPayments(MultipartFile file) {
        try {
            List<Payment> payments = CSVHelper.csvToPayments(file.getInputStream());
            paymentRepository.saveAll(payments);
        } catch (IOException e) {
            throw new CSVUploadException("failed to store csv data: " + e.getMessage(), ExceptionCode.CSV_UPLOAD_ERROR);
        }
    }

    public List<Payment> getAllPayments() {

        var payments = paymentRepository.findAll();

        if (payments.isEmpty())
            throw new PaymentsUnavailableException("No payments available at the moment", ExceptionCode.PAYMENTS_UNAVAILABLE);

        return payments;
    }

    public List<PaymentResponse> findByAllPendingPayments() {

        var payments = paymentRepository.findAllByPaymentStatus(PaymentStatus.PENDING);

        if (payments.isEmpty())
            throw new PaymentsUnavailableException("PENDING payments not found.", ExceptionCode.PAYMENTS_UNAVAILABLE);

        return payments.stream().map(a -> new PaymentResponse(a.getPaymentReference(), a.getEmployeeName(), a.getEmployeeCode(), a.getSalaryAmount(), a.getPaymentStatus())).toList();

    }

    public BigInteger sumOfPaymentsPerDay() {
        long sum = 0;
        var payments = getAllPayments();

        for (Payment payment : payments) {
            var num = BigInteger.valueOf(payment.getSalaryAmount().longValueExact());

            //TODO: set conditions for approval of summation
            return BigInteger.valueOf(num.longValueExact()).add(BigInteger.valueOf(sum));

        }
        return BigInteger.ZERO;
    }
}


//TODO:  reports, sum of payments


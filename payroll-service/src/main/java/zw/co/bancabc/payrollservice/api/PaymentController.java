package zw.co.bancabc.payrollservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zw.co.bancabc.commonutils.api.ApiResponse;
import zw.co.bancabc.payrollservice.business.service.PaymentService;
import zw.co.bancabc.payrollservice.payload.request.PaymentRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value = "/individual", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addIndividualPayment(@RequestParam("employee-code") String employeeCode, @RequestBody PaymentRequest paymentRequest) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.addIndividualPayment(employeeCode, paymentRequest));
    }

    @PostMapping(value = "/bulk/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse bulkPaymentsUpload(@RequestParam("file") MultipartFile file) {
        String message = "";

        paymentService.bulkUploadPayments(file);

        message = "File: " + file.getOriginalFilename()+ " uploaded successfully.";
        return new ApiResponse(HttpStatus.OK.value(), message);
    }


    @PutMapping(value = "/approve", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse approvePayment(@RequestParam("payment-reference") String paymentReference) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.approvePayment(paymentReference));
    }

    @PutMapping(value = "/reject", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse rejectPayment(@RequestParam("payment-reference") String paymentReference) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.rejectPayment(paymentReference));
    }

    @GetMapping(value = "/status/get", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse findPaymentsByStatus(@RequestParam("payment-status") String paymentStatus) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.findByPaymentsByStatus(paymentStatus));
    }

    @GetMapping(value = "/all/get", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse findAllPayments() {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.getAllPayments());
    }

    @GetMapping(value = "/pending/get", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse findAllPendingPayments() {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.findByAllPendingPayments());
    }

    @GetMapping(value = "/total/get", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse findAllSumOfPayments() {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), paymentService.sumOfPaymentsPerDay());
    }
}

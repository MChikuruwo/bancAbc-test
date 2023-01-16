package zw.co.bancabc.payrollservice.business.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;
import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;
import zw.co.bancabc.commonutils.exceptions.CSVParseException;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = { "id", "employeeName", "employeeCode", "salaryAmount", "paymentStatus","paymentReference", "approved"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Payment> csvToPayments(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Payment> payments = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            CSVFormat csvFormat = CSVFormat.DEFAULT;

            for (CSVRecord csvRecord : csvRecords) {

                Payment payment = new Payment(
                        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("employeeName"),
                        csvRecord.get("employeeCode"),
                        BigInteger.valueOf(Long.parseLong(csvRecord.get("salaryAmount"))),
                        PaymentStatus.valueOf(csvRecord.get("paymentStatus")),
                        csvRecord.get("paymentReference"),
                        Boolean.parseBoolean(csvRecord.get("approved"))
                );

                payments.add(payment);
            }

            return payments;
        } catch (IOException e) {
            throw new CSVParseException("fail to parse CSV file: " + e.getMessage(), ExceptionCode.CSV_PARSE_ERROR);
        }
    }

}
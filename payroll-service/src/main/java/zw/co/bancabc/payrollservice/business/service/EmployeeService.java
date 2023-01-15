package zw.co.bancabc.payrollservice.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.exceptions.EmailAddressNotFoundException;
import zw.co.bancabc.commonutils.exceptions.EmployeeCodeNotFoundException;
import zw.co.bancabc.payrollservice.business.model.Employee;
import zw.co.bancabc.payrollservice.business.repository.EmployeeRepository;
import zw.co.bancabc.payrollservice.payload.request.EmployeeRequest;
import zw.co.bancabc.payrollservice.payload.response.EmployeeResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse addNewEmployee(EmployeeRequest employeeRequest) {

        var employee = Employee.builder()
                .firstName(employeeRequest.firstName())
                .lastName(employeeRequest.lastName())
                .employeeCode(employeeRequest.employeeCode())
                .email(employeeRequest.email())
                .gender(employeeRequest.gender())
                .contract(employeeRequest.contract())
                .idNumber(employeeRequest.idNumber())
                .maritalStatus(employeeRequest.maritalStatus())
                .mobileNumber(employeeRequest.mobileNumber())
                .physicalAddress(employeeRequest.physicalAddress())
                .title(employeeRequest.title())
                .dateOfBirth(employeeRequest.dateOfBirth())
                .build();

        employee.setActive(true);

        employeeRepository.save(employee);

        return new EmployeeResponse(employee.getFirstName(), employeeRequest.lastName(), employeeRequest.employeeCode());

    }

    public Employee findEmployeeByEmployeeCode(String employeeCode) {

        var employee = employeeRepository.findEmployeeByEmployeeCode(employeeCode);

        if (employee.isEmpty())
            throw new EmployeeCodeNotFoundException("employee code does not exist", ExceptionCode.EMPLOYEE_CODE_NOT_FOUND);

        return employee.get();
    }

    public EmployeeResponse findEmployeeByEmail(String email) {

        var employee = employeeRepository.findEmployeeByEmail(new Email(email));

        if (employee.isEmpty())
            throw new EmailAddressNotFoundException("email address does not exist", ExceptionCode.EMAIL_NOT_FOUND);

        return new EmployeeResponse(employee.get().getFirstName(), employee.get().getLastName(), employee.get().getEmployeeCode());
    }
}

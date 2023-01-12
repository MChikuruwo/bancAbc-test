package zw.co.bancabc.payrollservice.api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.bancabc.commonutils.api.ApiResponse;
import zw.co.bancabc.payrollservice.business.service.EmployeeService;
import zw.co.bancabc.payrollservice.payload.request.EmployeeRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {

        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), employeeService.addNewEmployee(employeeRequest));
    }

    @GetMapping(value = "/employee-code/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve employee by employee code")
    public ApiResponse getEmployeeByEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), employeeService.findEmployeeByEmployeeCode(employeeCode));
    }

    @GetMapping(value = "/email-address/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve employee by email address")
    public ApiResponse getEmployeeByEmail(@RequestParam("email") String email) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), employeeService.findEmployeeByEmail(email));
    }
}

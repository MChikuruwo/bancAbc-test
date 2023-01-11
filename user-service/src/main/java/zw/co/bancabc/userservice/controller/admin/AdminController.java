package zw.co.bancabc.userservice.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.bancabc.commonutils.api.ApiResponse;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.userservice.service.UserService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/api/v1/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RolesAllowed("ADMIN")
public class AdminController {

    private final UserService userService;

    @GetMapping("/")
    public ApiResponse getAllUsers() {
        return new ApiResponse(HttpStatus.CREATED.OK.value(), "SUCCESS", userService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse getOneUser(@PathVariable("id") Long id) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), userService.getOne(id));
    }

    @GetMapping("/email")
    public ApiResponse findByEmail(@RequestParam("email") Email email) {
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), userService.findByEmail(email));
    }

}
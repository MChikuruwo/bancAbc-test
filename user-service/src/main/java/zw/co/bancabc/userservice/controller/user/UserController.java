package zw.co.bancabc.userservice.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.bancabc.commonutils.api.ApiResponse;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.login.PasswordResetDto;
import zw.co.bancabc.commonutils.register.CreateUserRequest;
import zw.co.bancabc.userservice.domain.dto.user.UpdateUserDto;
import zw.co.bancabc.userservice.domain.model.User;
import zw.co.bancabc.userservice.service.RoleService;
import zw.co.bancabc.userservice.service.UserService;

import java.util.Collections;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    @PostMapping(value = "/signUp/initiator", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Sign up a user as initiator", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse signUpMobile(@RequestBody CreateUserRequest addUserDto) {
        var user = modelMapper.map(addUserDto, User.class);

        // Assign the role of the user
        user.setRole(Collections.singleton(roleService.findByName("INITIATOR")));

        user.setActive(true);

        return new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.OK.name(), userService.add(user));
    }

    @PostMapping(value = "/signUp/approver", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Sign up a user as approver", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse signUpUssd(@RequestBody CreateUserRequest addUserDto) {

        var user = modelMapper.map(addUserDto, User.class);

        user.setRole(Collections.singleton(roleService.findByName("APPROVER")));

        user.setActive(true);

        return new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.OK.name(), userService.add(user));
    }

    @PostMapping(value = "/signUp/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Sign up a admin to the platform", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse signUpAdmin(@RequestBody CreateUserRequest addUserDto) {
        var user = modelMapper.map(addUserDto, User.class);

        user.setRole(Collections.singleton(roleService.findByName("ADMIN")));

        user.setActive(true);

        return new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.OK.name(), userService.add(user));
    }

    @GetMapping("/current")
    @ApiOperation(value = "Get currently logged user", response = ApiResponse.class)
    public User getCurrentActualUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/mobile")
    public User findByMobileNumber(@RequestParam("mobileNumber") MobileNumber mobileNumber) {
        return userService.findByMobileNumber(mobileNumber);
    }

    @PutMapping("/reset-pin")
    @ApiOperation(value = "Password reset for user.", response = ApiResponse.class)
    public ApiResponse resetUserPin(@RequestBody PasswordResetDto credentialsDto,
                                    @RequestParam MobileNumber mobileNumber) {

        // Get user by their email address
        User user = userService.findByMobileNumber(mobileNumber);

        // Set the user password to the generated password
        user.setPassword(credentialsDto.password());

        return new ApiResponse(200, "SUCCESS", userService.update(user));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a current user's details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateUser(@RequestParam MobileNumber mobileNumber,
                                  @RequestBody UpdateUserDto updateUserDto) {
        User user = modelMapper.map(updateUserDto, User.class);
        userService.findByMobileNumber(mobileNumber);
        return new ApiResponse(200, "SUCCESS", userService.update(user));
    }


    @GetMapping("/verify-token")
    ApiResponse verifyToken(@RequestParam("token") String token) {
        return new ApiResponse(200, "SUCCESS", userService.verifyToken(token));
    }
}
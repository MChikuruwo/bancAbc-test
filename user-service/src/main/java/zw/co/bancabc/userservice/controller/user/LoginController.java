package zw.co.bancabc.userservice.controller.user;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.bancabc.commonutils.api.ApiResponse;
import zw.co.bancabc.commonutils.domain.response.LogInResponse;
import zw.co.bancabc.commonutils.domain.value.login.LoginDto;
import zw.co.bancabc.commonutils.security.JwtTokenProvider;
import zw.co.bancabc.userservice.domain.model.Login;
import zw.co.bancabc.userservice.service.LoginService;
import zw.co.bancabc.userservice.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoginController {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final LoginService loginService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @SneakyThrows
    @PostMapping(value = "/login")
    public ResponseEntity loginWithMobileAndPassword(@RequestBody LoginDto accountCredentials) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        accountCredentials.email(),
                        accountCredentials.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        //Check if the authentication was successful. If it is, then return the details of the user

        ApiResponse response;

        if (authentication.isAuthenticated()) {

            var authenticatedUser = userService.findByEmail(accountCredentials.email()).get();

            // Log user login in database
            var login = new Login();
            login.setUser(authenticatedUser);
            loginService.add(login);

            response = new ApiResponse(200, "SUCCESS", new LogInResponse(authenticatedUser.getFirstName(),
                    authenticatedUser.getLastName(), authenticatedUser.getUserName()));
            return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + " " + jwt).body(response);
        } else {
            response = new ApiResponse(400, "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN).body(response);
        }
    }
}
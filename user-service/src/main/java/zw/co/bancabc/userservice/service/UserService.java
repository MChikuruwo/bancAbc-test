package zw.co.bancabc.userservice.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;
import zw.co.bancabc.commonutils.domain.response.UserResponse;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.UserName;
import zw.co.bancabc.commonutils.exceptions.*;
import zw.co.bancabc.commonutils.security.JwtTokenProvider;
import zw.co.bancabc.userservice.domain.model.Role;
import zw.co.bancabc.userservice.domain.model.User;
import zw.co.bancabc.userservice.domain.model.UserPrincipal;
import zw.co.bancabc.userservice.domain.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;


    public UserResponse add(final User user) {
        var mobileNumberFromDatabase = Optional.ofNullable(userRepository.findUserByMobileNumber(user.getMobileNumber()));

        var userNameFromDatabase = Optional.ofNullable(userRepository.findUserByUserName(user.getUserName()));

        if (mobileNumberFromDatabase.isPresent())
            throw new UserAlreadyExistsException("mobile.number " + user.getMobileNumber() + " already.exists", ExceptionCode.USER_ALREADY_EXISTS);

        if (userNameFromDatabase.isPresent())
            throw new UserNameAlreadyExistsException("user.name " + user.getUserName() + " already.taken", ExceptionCode.USER_ALREADY_EXISTS);

        var pin = user.getPassword();

        if (!NumberUtils.isDigits(pin))
            throw new InvalidUserPinException("user.pin.must.contain.numbers.only", ExceptionCode.INVALID_USER_PIN);

        if (pin.length() != 4)
            throw new InvalidUserPinException("pin.length.must.be.4", ExceptionCode.INVALID_USER_PIN);
        user.setPassword(passwordEncoder.encode(pin));

//        userRepository.save(user);

        return new UserResponse(user.getFirstName(), user.getLastName(),
                user.getMobileNumber(), user.getUserName(), user.getEmail());
    }

    public void authenticateUser(String pin) {

        var user = getCurrentUser();

        if (!passwordEncoder.matches(pin, user.getPassword())) {
            throw new InvalidUserPinException("invalid.user.pin", ExceptionCode.INVALID_USER_PIN);
        }
    }

    public User getCurrentUser() {
        return findUserByPrincipal(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
                .orElseThrow(() -> new UserAuthenticationErrorException("current.user.is.not.authenticated.properly", ExceptionCode.USER_AUTHENTICATION_ERROR));

    }

    public String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof String principal) {

            if (principal.compareTo("anonymousUser") != 0) {
                return null;
            }
            return principal;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    public Optional<User> findUserByPrincipal(final String principal) {

        if (MobileNumber.isValidPhoneNumber(principal))
            return Optional.ofNullable(userRepository.findUserByMobileNumber(new MobileNumber(principal)));

        if (UserName.isValidUsername(principal))
            return Optional.ofNullable(userRepository.findUserByUserName(new UserName(principal)));

        return Optional.empty();
    }

    public User update(User user) {
        var userFromDatabase = Optional.ofNullable(userRepository.findUserByMobileNumber(user.getMobileNumber()));

        if (userFromDatabase.isEmpty())
            throw new UserNotFoundException("User does not exist", ExceptionCode.USER_NOT_FOUND);
        // Carry date created timestamp
        user.setCreatedDate(userFromDatabase.get().getCreatedDate());

        //set new pin by user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String delete(Long id) {
        var userToDelete = userRepository.findById(id);
        if (!userToDelete.isPresent()) {
            throw new UserNotFoundException("User with ID " + id + " does not exist", ExceptionCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);

        return "User has been successfully deleted";
    }

    public List<UserResponse> getAll() {
        var users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UsersNotAvailableException("Users not found", ExceptionCode.USERS_UNAVAILABLE);
        }
        return users.stream().map(a -> (new UserResponse(a.getFirstName(), a.getLastName(), a.getMobileNumber(), a.getUserName(), a.getEmail()))).toList();
    }

    public User getOne(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with the ID " + id + " does not exist", ExceptionCode.USER_NOT_FOUND);
        }
        return user.get();
    }

    public User authUser(MobileNumber mobileNumber, String pin) throws Exception {
        //First get the user by mobileNumber to check if the user exists
        var user = Optional.ofNullable(userRepository.findUserByMobileNumber(mobileNumber));

        if (user.isEmpty()) {
            //Display an error that the user with the userName given was not found
            throw new MobileNumberNotFoundException("User with mobile number: " + mobileNumber + " not found", ExceptionCode.MOBILE_NUMBER_NOT_FOUND);
        }
        //Check user entered password if it matches hashed password in database
        if (!passwordEncoder.matches(pin, user.get().getPassword().toString())) {
            throw new BadCredentialsException("Incorrect Pin");
        }
        //Else return the user if found
        return user.get();
    }

    public User findByMobileNumber(MobileNumber mobileNumber) {
        var user = userRepository.findUserByMobileNumber(mobileNumber);
        return user;
    }

    public User findByUserName(UserName userName) {
        var user = Optional.ofNullable(userRepository.findUserByUserName(userName));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with the userName " + userName + " not found", ExceptionCode.USER_NOT_FOUND);
        }
        return user.get();
    }

    public boolean verifyToken(String token) {

        return tokenProvider.validateToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        var user = userRepository.findUserByMobileNumber(new MobileNumber(mobileNumber));
        user.getRole().toString();
        Set<GrantedAuthority> grantedAuthorities = null;
        try {
            user = userRepository.findUserByMobileNumber(new MobileNumber(mobileNumber));
            if (user == null)
                throw new MobileNumberNotFoundException("Mobile.number: " + mobileNumber + " not.found.", ExceptionCode.MOBILE_NUMBER_NOT_FOUND);

            grantedAuthorities = new HashSet<>();
            for (Role role : user.getRole()) {
                String roleName = "" + role.getName();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
                grantedAuthorities.add(grantedAuthority);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        if (user == null) {
            //TODO: Set an error that the user by that email address cannot be found
            throw new MobileNumberNotFoundException("mobile.number:" + mobileNumber + " not.found.", ExceptionCode.MOBILE_NUMBER_NOT_FOUND);

        }
        return new UserPrincipal(user, user.getPassword(), grantedAuthorities);
    }

    public Optional<User> findByEmail(Email email) {
        var user = userRepository.findUserByEmail(email);

        if (user.isEmpty())
            throw new EmailAddressNotFoundException("email.address.not.found", ExceptionCode.EMAIL_NOT_FOUND);

        return user;
    }


}
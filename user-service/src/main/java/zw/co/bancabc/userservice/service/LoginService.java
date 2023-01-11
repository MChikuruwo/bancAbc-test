package zw.co.bancabc.userservice.service;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;
import zw.co.bancabc.commonutils.exceptions.LoginEntriesUnavailableException;
import zw.co.bancabc.commonutils.exceptions.LoginEntryUnavailableException;
import zw.co.bancabc.commonutils.exceptions.UserLoginEntriesUnavailableException;
import zw.co.bancabc.commonutils.exceptions.UserNotFoundException;
import zw.co.bancabc.userservice.domain.model.Login;
import zw.co.bancabc.userservice.domain.model.User;
import zw.co.bancabc.userservice.domain.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final UserService userService;

    public Login add(Login login) {
        var user = userService.findByMobileNumber(login.getUser().getMobileNumber());
        if(user==null) {
            throw new UserNotFoundException("user.with.mobile.number "+user.getMobileNumber()+" not found", ExceptionCode.USER_NOT_FOUND);
        }
        return loginRepository.save(login);
    }

    public List<Login> getAll() {
        var loginList = loginRepository.findAll();
        if (loginList.isEmpty()) {
            throw new EntityNotFoundException("No login entries found");
        }
        return loginList;
    }

    public Login getOne(Long id) {
        var login = loginRepository.findById(id);
        if (login.isEmpty()) {
            throw new LoginEntryUnavailableException("Login with ID " + id + " not found", ExceptionCode.LOGIN_ENTRY_UNAVAILABLE);
        }
        return login.get();
    }

    public List<Login> findAllByUser(User user) {
        var loginList = loginRepository.findAllByUser(user);
        if (loginList.isEmpty()) {
            throw new UserLoginEntriesUnavailableException("Login entries by user " + user.getMobileNumber() + " not found",ExceptionCode.USER_LOGIN_ENTRY_UNAVAILABLE);
        }
        return loginList;
    }

    public List<Login> findAllByDate(Date date) {
        List<Login> loginList = loginRepository.findAllByCreatedDate(date);
        if (loginList.isEmpty()) {
            throw new LoginEntriesUnavailableException("No login entries found for the date " + date.toString(),ExceptionCode.LOGIN_ENTRIES_UNVAILABLE);
        }
        return loginList;
    }
}
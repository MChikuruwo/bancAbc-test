package zw.co.bancabc.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;
import zw.co.bancabc.commonutils.exceptions.ActivityNotFoundException;
import zw.co.bancabc.userservice.domain.model.UserActivity;
import zw.co.bancabc.userservice.domain.repository.ActivityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public UserActivity add(UserActivity activity) {
        return activityRepository.save(activity);
    }

    public UserActivity getById(Long id) {
        Optional<UserActivity> fromDateActivity = activityRepository.findById(id);

        if (!fromDateActivity.isPresent())
            throw new ActivityNotFoundException("Activity with ID " + id + " not found!", ExceptionCode.ACTIVITY_NOT_FOUND);
        return fromDateActivity.get();
    }

    public List<UserActivity> getAll() {
        return activityRepository.findAll();
    }

    public List<UserActivity> getAllByEntityId(Long entityId) {
        return activityRepository.getAllByEntityId(entityId);
    }
}
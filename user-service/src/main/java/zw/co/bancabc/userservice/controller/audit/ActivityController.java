package zw.co.bancabc.userservice.controller.audit;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.bancabc.commonutils.api.ApiResponse;
import zw.co.bancabc.userservice.domain.dto.audit.ActivityDto;
import zw.co.bancabc.userservice.domain.model.UserActivity;
import zw.co.bancabc.userservice.service.ActivityService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/activity", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {
    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    @Autowired
    public ActivityController(ActivityService activityService, ModelMapper modelMapper) {
        this.activityService = activityService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "logging user activity ", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addActivity(@RequestBody ActivityDto activityDto) {
        var activity = modelMapper.map(activityDto, UserActivity.class);
        return new ApiResponse(200, "SUCCESS", activityService.add(activity));
    }

    @GetMapping("/")
//    @ApiOperation(value = "Get all activities", response = ApiResponse.class)
    public ApiResponse getAllActivities() {
        return new ApiResponse(200, "SUCCESS", activityService.getAll());
    }

    @GetMapping("/{id}")
//    @ApiOperation(value = "Get single activity by its ID", response = ApiResponse.class)
    public ApiResponse getById(@PathVariable("id") Long id) {
        return new ApiResponse(200, "SUCCESS", activityService.getById(id));
    }

    @GetMapping("/{entityId}")
//    @ApiOperation(value = "Get all activities performed on a particular entity", response = ApiResponse.class)
    public ApiResponse getAllActivitiesPerformedOnEntity(@PathVariable("entityId") Long entityId) {
        return new ApiResponse(200, "SUCCESS", activityService.getAllByEntityId(entityId));
    }
}
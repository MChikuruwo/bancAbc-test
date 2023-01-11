package zw.co.bancabc.commonutils.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.bancabc.commonutils.api.ApiResponse;

@FeignClient(name = "user-service")
public interface AuthServiceFeignClient {

    @GetMapping("/api/v1/users/verify-token")
    ApiResponse verifyToken(@RequestParam("token") String token);
}
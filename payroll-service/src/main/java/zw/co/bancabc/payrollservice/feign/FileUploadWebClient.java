package zw.co.bancabc.payrollservice.feign;

import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import zw.co.bancabc.commonutils.config.LocalRibbonClientConfiguration;
import zw.co.bancabc.commonutils.domain.response.UploadFileResponse;
import zw.co.bancabc.payrollservice.business.model.FileStorage;

@FeignClient(value = "HCP-USSD", decode404 = true, url = "${spring.feign.file-upload.client}")
@Headers({"X-Application-Context", "firstmutual-ussd:1980"})
@RibbonClient(value = "FILE-STORAGE",configuration = LocalRibbonClientConfiguration.class)
public interface FileUploadWebClient {


    @PostMapping(value = "/api/v1/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file);

    @GetMapping("/api/v1/file/get/{fileId}")
    FileStorage getFile(@PathVariable String fileId);
}

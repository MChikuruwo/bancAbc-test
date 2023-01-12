package zw.co.bancabc.payrollservice.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.co.bancabc.commonutils.domain.response.UploadFileResponse;
import zw.co.bancabc.payrollservice.business.model.FileStorage;
import zw.co.bancabc.payrollservice.feign.FileUploadWebClient;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUploadWebClient fileUploadWebClient;

    public UploadFileResponse uploadFile(MultipartFile file) {
        return fileUploadWebClient.uploadFile(file);
    }

    public FileStorage getFile(String fileId) {
        return fileUploadWebClient.getFile(fileId);
    }
}
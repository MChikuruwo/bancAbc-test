package zw.co.bancabc.filestorageservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.bancabc.commonutils.domain.response.UploadFileResponse;
import zw.co.bancabc.filestorageservice.business.model.FileStorage;
import zw.co.bancabc.filestorageservice.business.service.FileStorageService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/file")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService dbFileStorageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        FileStorage dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }


    @PostMapping(value = "/uploadMultiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/{fileId}")
    public FileStorage getFile(@PathVariable String fileId) {
        // Load file from database
        var dbFile = dbFileStorageService.getFile(fileId);

        return dbFile;
    }
}
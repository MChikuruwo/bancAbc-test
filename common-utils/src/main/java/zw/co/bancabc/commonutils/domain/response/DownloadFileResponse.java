package zw.co.bancabc.commonutils.domain.response;

import org.springframework.http.ResponseEntity;

public record DownloadFileResponse(String fileName, ResponseEntity response) {
}
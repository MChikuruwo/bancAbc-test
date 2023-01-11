package zw.co.bancabc.filestorageservice.business.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import zw.co.bancabc.filestorageservice.business.model.FileStorage;
import zw.co.bancabc.filestorageservice.business.repository.FileRepository;
import zw.co.bancabc.filestorageservice.exception.FileNotFoundException;
import zw.co.bancabc.filestorageservice.exception.FileStorageException;

import java.io.IOException;

/**
 * @author M.Chikuruwo
 * @created 06/01/2022 - 13:32
 */

@Slf4j
@Component
@Service
@AllArgsConstructor
public class FileStorageService {

    private final FileRepository dbFileRepository;

    public FileStorage storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileStorage dbFile = new FileStorage(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public FileStorage getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
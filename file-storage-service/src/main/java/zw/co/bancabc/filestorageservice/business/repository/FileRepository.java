package zw.co.bancabc.filestorageservice.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.bancabc.filestorageservice.business.model.FileStorage;

@Repository
public interface FileRepository extends JpaRepository<FileStorage, String> {
}
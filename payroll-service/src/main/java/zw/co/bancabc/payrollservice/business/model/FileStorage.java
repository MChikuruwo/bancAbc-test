package zw.co.bancabc.payrollservice.business.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import zw.co.bancabc.commonutils.domain.value.AbstractAuditingEntity;

import javax.persistence.*;

@Entity
@Table(schema = "file_upload", name = "file_storage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class FileStorage extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
}
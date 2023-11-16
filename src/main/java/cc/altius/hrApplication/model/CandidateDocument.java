package cc.altius.hrApplication.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Akil Mahimwala
 * @createdOn 2017/12/24
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CandidateDocument {

    @EqualsAndHashCode.Include
    private int candidateDocumentId;
    private int candidateId;
    private String filePath;
    private String fileName;
    private MultipartFile multipartFile;
    private List<MultipartFile> files;

}

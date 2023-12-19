package com.emrekentli.adoptme.domain.platform.media.api;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MediaDto {
    private List<MultipartFile> files;
    private String base64Type;
    private String filePath;
    private String fileName;
}

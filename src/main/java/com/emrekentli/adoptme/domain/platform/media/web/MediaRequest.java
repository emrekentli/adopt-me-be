package com.emrekentli.adoptme.domain.platform.media.web;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MediaRequest {
    private List<MultipartFile> files;
}

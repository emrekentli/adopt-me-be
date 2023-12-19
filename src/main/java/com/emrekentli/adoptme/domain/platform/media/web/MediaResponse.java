package com.emrekentli.adoptme.domain.platform.media.web;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MediaResponse {
    private String fileName;
    private String filePath;
    private String base64Type;
}

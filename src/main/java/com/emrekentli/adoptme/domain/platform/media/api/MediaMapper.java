package com.emrekentli.adoptme.domain.platform.media.api;


import com.emrekentli.adoptme.domain.platform.media.web.MediaRequest;
import com.emrekentli.adoptme.domain.platform.media.web.MediaResponse;

import java.util.List;

public class MediaMapper {
    private MediaMapper() {
    }
    public static MediaDto toDto(MediaRequest request) {
        return MediaDto.builder()
                .files(request.getFiles())
                .build();
    }
    public static MediaResponse toResponse(MediaDto dto) {
        return MediaResponse.builder()
                .fileName(dto.getFileName())
                .filePath(dto.getFilePath())
                .base64Type(dto.getBase64Type())
                .build();
    }
    public static List<MediaResponse> toDataResponse(List<MediaDto> dtos) {
        return dtos.stream()
                .map(MediaMapper::toResponse)
                .toList();
    }
}

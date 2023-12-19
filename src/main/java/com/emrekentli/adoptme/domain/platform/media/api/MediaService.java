package com.emrekentli.adoptme.domain.platform.media.api;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaDto uploadMedia(MediaDto dto);

    MediaDto uploadMediaSingle(MultipartFile file);

    List<MediaDto> getAllMediaBase64();

    void deleteMedia(String fileName);
}

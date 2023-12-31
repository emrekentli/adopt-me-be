package com.emrekentli.adoptme.domain.platform.media.impl;

import com.emrekentli.adoptme.domain.platform.media.api.MediaDto;
import com.emrekentli.adoptme.domain.platform.media.api.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    @Override
    public MediaDto uploadMedia(MediaDto dto) {
            String filePath = checkMediaDirectory().getAbsolutePath();
            dto.getFiles().forEach(multipartFile -> fileTransfer(multipartFile, filePath));
            dto.setFilePath(filePath);
            dto.setFileName(dto.getFiles().get(0).getOriginalFilename());
            return dto;
    }

    @Override
    public MediaDto uploadMediaSingle(MultipartFile file) {
        // Ensure that the media directory exists, and get its absolute path
        String filePath = checkMediaDirectory().getAbsolutePath();

        // Transfer the file to the media directory
        fileTransfer(file, filePath);

        try {
            // Read the content of the file into a byte array
            byte[] fileContent = file.getBytes();

            // Create a MediaDto object with the necessary information
            MediaDto mediaDto = MediaDto.builder()
                    .filePath(filePath)
                    .base64Type(Base64.getEncoder().encodeToString(fileContent))
                    .fileName(file.getOriginalFilename())
                    .build();

            return mediaDto;
        } catch (IOException e) {
            // Handle the exception appropriately (e.g., log it or throw a custom exception)
            throw new RuntimeException("Failed to read file content", e);
        }
    }
    @Override
    public List<MediaDto> getAllMediaBase64() {
        try (Stream<Path> paths = Files.list(checkMediaDirectory().toPath())) {
            return paths.map(file -> {
                try {
                    return MediaDto.builder()
                            .base64Type(Base64.getEncoder().encodeToString(Files.readAllBytes(file)))
                            .filePath(file.toString())
                            .build();
                } catch (IOException e) {
                    throw new UncheckedIOException("Base64'e çevirilirken hata oluştu.", e);
                }
            }).toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Medya okunurken hata oluştu.", e);
        }
    }
    @Override
    public void deleteMedia(String fileName) {
        try {
            Files.delete(Paths.get(checkMediaDirectory().getAbsolutePath(), fileName));
        } catch (IOException e) {
            throw new UncheckedIOException("Dosya silinirken hata oluştu.", e);
        }
    }
    private void fileTransfer(MultipartFile file, String filePath) {
        try {
            file.transferTo(Paths.get(filePath + "/" + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new UncheckedIOException("Medya yüklenirken hata oluştu.", e);
        }
    }
    private File checkMediaDirectory() {
        ClassLoader classLoader = getClass().getClassLoader();
        String baseDirectory = classLoader.getResource(".").getFile();
        File file = new File(baseDirectory + "/media");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}

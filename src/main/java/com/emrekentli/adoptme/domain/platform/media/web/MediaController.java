package com.emrekentli.adoptme.domain.platform.media.web;

import com.emrekentli.adoptme.domain.platform.media.api.MediaMapper;
import com.emrekentli.adoptme.domain.platform.media.api.MediaService;
import com.emrekentli.adoptme.library.rest.BaseController;
import com.emrekentli.adoptme.library.rest.DataResponse;
import com.emrekentli.adoptme.library.rest.MetaResponse;
import com.emrekentli.adoptme.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("media")
@RequiredArgsConstructor
public class MediaController extends BaseController {
    private final MediaService service;
    @PostMapping
    public Response<MediaResponse> uploadMedia(MediaRequest request) {
        return respond(MediaMapper.toResponse(service.uploadMedia(MediaMapper.toDto(request))));
    }

    @PostMapping("/single")
    public Response<MediaResponse> uploadMedia(@RequestPart("file")MultipartFile file) {
        return respond(MediaMapper.toResponse(service.uploadMediaSingle(file)));
    }
    @GetMapping
    public Response<DataResponse<MediaResponse>> getAllMediaBase64() {
        return respond(MediaMapper.toDataResponse(service.getAllMediaBase64()));
    }
    @DeleteMapping("/{fileName}")
    public Response<Void> deleteMedia(@PathVariable String fileName) {
        service.deleteMedia(fileName);
        return new Response<>(MetaResponse.success());
    }
}

package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.ImageRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.ImageIdResponse;
import com.project.warmyhomes.payload.response.business.ImageResponse;
import com.project.warmyhomes.service.business.ImageService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{imageId}") //http://localhost:8080/images/:imageId + GET
    public ResponseMessage<ImageResponse> getImageById(@PathVariable Long imageId) {
        return imageService.getImageById(imageId);
    }

    @PostMapping("/{advertId}") //http://localhost:8080/images/:advertId + POST + JSON
    @PreAuthorize("hasAnyAuthority('Admin','Manager', 'Customer')")
    public ResponseMessage<List<ImageIdResponse>> uploadImages(@Valid @ModelAttribute ImageRequest imageRequest, @PathVariable Long advertId) {
        return imageService.uploadImages(imageRequest, advertId);
    }

    @DeleteMapping("/{imageIds}") //http://localhost:8080/images/:imageIds+ DELETE
    @PreAuthorize("hasAnyAuthority('Admin','Manager', 'Customer')")
    public void deleteImageByIds(@PathVariable List<Long> imageIds) {
        imageService.deleteImageByIds(imageIds);
    }

    @PutMapping("/{imageId}") //http://localhost:8080/images/:imageId + DELETE
    @PreAuthorize("hasAnyAuthority('Admin','Manager', 'Customer')")
    public ResponseMessage<ImageResponse> updateImageById(@PathVariable Long imageId) {
        return imageService.updateImageById(imageId);
    }
}

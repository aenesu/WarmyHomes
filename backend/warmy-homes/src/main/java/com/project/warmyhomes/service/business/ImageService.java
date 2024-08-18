package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.entity.concretes.business.Image;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.ImageMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.ImageRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.ImageIdResponse;
import com.project.warmyhomes.payload.response.business.ImageResponse;
import com.project.warmyhomes.repository.business.ImageRepository;

import com.project.warmyhomes.service.helper.MethodHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    private final MethodHelper methodHelper;


    /**
     * Retrieve an image from the system based on the provided image ID.
     * <p>
     * This method searches for an image using the specified ID. If the image is found, it maps the image entity to an ImageResponse object and returns it within a ResponseMessage. The ResponseMessage includes a success message and an HTTP status of 200 (OK).
     *
     * @param imageId The unique identifier of the image to be retrieved.
     * @return A ResponseMessage containing the ImageResponse object with the image details, along with a success message and HTTP status.
     */
    public ResponseMessage<ImageResponse> getImageById(Long imageId) {
        Image image = methodHelper.isImageById(imageId);

        return ResponseMessage.<ImageResponse>builder()
                .message(SuccessMessages.IMAGES_FOUND)
                .object(imageMapper.imageToImageResponse(image))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Upload a list of images associated with a specific advert.
     * <p>
     * This method processes and uploads multiple images as specified in the provided ImageRequest. It performs validation checks on each image, including file name, file extension, content type, and size. Images are validated to ensure they are of allowed formats (JPG, JPEG, PNG, SVG, WEBP) and do not exceed 5MB in size. The images are then saved to the repository with the associated advert information.
     *
     * @param imageRequest An object containing the images to be uploaded, represented as an array of MultipartFile.
     * @param advertId     The unique identifier of the advert with which the images are associated.
     * @return A ResponseMessage containing a list of ImageIdResponse objects with the IDs of the successfully uploaded images, along with a success message and HTTP status.
     * @throws IllegalArgumentException If any image fails validation checks (e.g., invalid format, size, or type).
     * @throws IllegalStateException    If an error occurs while processing or saving the images.
     */
    public ResponseMessage<List<ImageIdResponse>> uploadImages(ImageRequest imageRequest, Long advertId) {
        Advert advert = methodHelper.isAdvertById(advertId);

        List<Image> savedImages = Arrays.stream(imageRequest.getImages())
                .map(imageFile -> {
                    try {

                        if (imageFile.getOriginalFilename() == null || imageFile.getOriginalFilename().isEmpty()) {
                            throw new IllegalArgumentException("Image name cannot be empty");
                        }

                        String fileExtension = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

                        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "svg", "webp");

                        if (!allowedExtensions.contains(fileExtension)) {
                            throw new IllegalArgumentException("Only image files (JPG, JPEG, PNG, SVG, WEBP) are allowed");
                        }

                        if (imageFile.getContentType() == null || !imageFile.getContentType().startsWith("image/")) {
                            throw new IllegalArgumentException("Only image files are allowed");
                        }

                        if (imageFile.getSize() > 5000000) {
                            throw new IllegalArgumentException("Image size cannot exceed 5MB");
                        }

                        Image image = Image.builder()
                                .data(imageFile.getBytes())
                                .name(imageFile.getOriginalFilename())
                                .type(imageFile.getContentType())
                                .featured(false)
                                .advert(advert).build();
                        return imageRepository.save(image);

                    } catch (IOException e) {
                        throw new IllegalStateException("Failed to process image: " + imageFile.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());

        List<ImageIdResponse> imageIdResponses = savedImages.stream()
                .map(imageMapper::imageToImageIdResponse)
                .collect(Collectors.toList());

        return ResponseMessage.<List<ImageIdResponse>>builder()
                .message(SuccessMessages.IMAGES_UPLOAD)
                .object(imageIdResponses)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Delete images identified by the specified list of image IDs.
     * <p>
     * This method iterates through the provided list of image IDs and deletes each corresponding image from the repository. Before deletion, it verifies that each image ID exists in the repository. If an image ID is not found, a ResourceNotFoundException is thrown with a message indicating the missing image.
     *
     * @param imageIds A list of unique identifiers for the images to be deleted.
     * @throws ResourceNotFoundException If any image ID in the list does not exist in the repository.
     */
    public void deleteImageByIds(List<Long> imageIds) {
        imageIds.forEach(imageId -> {
            if (!imageRepository.existsById(imageId)) {
                throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_IMAGE_MESSAGE, imageId));
            }
            imageRepository.deleteById(imageId);
        });
    }

    /**
     * Update the specified image to be the featured image for its associated advert.
     * <p>
     * This method marks the image identified by the given ID as the featured image for its associated advert. It first checks if there is already a featured image for the advert. If so, it unsets the featured status of the existing featured image. Then, it sets the provided image as the new featured image and saves the update to the repository.
     *
     * @param imageId The unique identifier of the image to be updated as the featured image.
     * @return A ResponseMessage containing the updated ImageResponse object for the featured image, along with a success message and HTTP status.
     */
    public ResponseMessage<ImageResponse> updateImageById(Long imageId) {

        Image image = methodHelper.isImageById(imageId);

        Image featuredImage = imageRepository.findFeaturedImageByAdvertId(image.getAdvert().getId());

        if (featuredImage != null && !featuredImage.getId().equals(image.getId())) {
            featuredImage.setFeatured(false);
            imageRepository.save(featuredImage);
        }

        image.setFeatured(true);
        Image savedFeaturedImage = imageRepository.save(image);

        return ResponseMessage.<ImageResponse>builder()
                .message(SuccessMessages.IMAGES_UPDATED)
                .object(imageMapper.imageToImageResponse(savedFeaturedImage))
                .httpStatus(HttpStatus.OK)
                .build();
    }

}


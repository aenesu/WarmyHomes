package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.AdvertType;
import com.project.warmyhomes.exception.BadRequestException;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.AdvertTypeMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.AdvertTypeRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertTypeResponse;
import com.project.warmyhomes.repository.business.AdvertRepository;
import com.project.warmyhomes.repository.business.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertRepository advertRepository;

    private final AdvertTypeMapper advertTypeMapper;

    /**
     * Retrieve all AdvertTypes.
     *
     * @return a ResponseEntity containing the list of AdvertTypeResponse and HTTP status
     */
    public ResponseEntity<List<AdvertTypeResponse>> getAllAdvertTypes() {
        List<AdvertTypeResponse> advertTypeResponseList = advertTypeRepository.findAll()
                .stream()
                .map(advertTypeMapper::mapAdvertTypeToAdvertTypeResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(advertTypeResponseList);
    }

    /**
     * Retrieve an AdvertType by its ID.
     *
     * @param advertTypeId the ID of the AdvertType to be retrieved
     * @return a ResponseMessage containing the AdvertTypeResponse, success message, and HTTP status
     */
    public ResponseMessage<AdvertTypeResponse> getAdvertTypeById(Long advertTypeId) {
        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_FOUND)
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(getAdvertTypeByAdvertTypeId(advertTypeId)))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Create a new AdvertType based on the provided AdvertTypeRequest.
     *
     * @param advertTypeRequest the request object containing the details for the AdvertType to be created
     * @return a ResponseMessage containing the created AdvertTypeResponse, success message, and HTTP status
     */
    public ResponseMessage<AdvertTypeResponse> createAdvertType(AdvertTypeRequest advertTypeRequest) {
        AdvertType advertType = advertTypeMapper.mapAdvertTypeRequestToAdvertType(advertTypeRequest);
        AdvertType createdAdvertType = advertTypeRepository.save(advertType);

        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_CREATE)
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(createdAdvertType))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }


    /**
     * Update an existing AdvertType based on the provided AdvertTypeRequest and ID.
     *
     * @param advertTypeRequest the request object containing the details for the AdvertType to be updated
     * @param advertTypeId      the ID of the AdvertType to be updated
     * @return a ResponseMessage containing the updated AdvertTypeResponse, success message, and HTTP status
     */
    public ResponseMessage<AdvertTypeResponse> updateAdvertType(AdvertTypeRequest advertTypeRequest, Long advertTypeId) {
        AdvertType advertType = getAdvertTypeByAdvertTypeId(advertTypeId);

        advertType.setTitle(advertTypeRequest.getTitle());

        AdvertType updatedAdvertType = advertTypeRepository.save(advertType);

        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_UPDATE)
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(updatedAdvertType))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Delete an AdvertType by its ID.
     *
     * @param advertTypeId the ID of the AdvertType to be deleted
     * @return a ResponseMessage containing the deleted AdvertTypeResponse, success message, and HTTP status
     * @throws BadRequestException if the AdvertType is built-in or has related adverts
     */
    public ResponseMessage<AdvertTypeResponse> deleteAdvertType(Long advertTypeId) {
        AdvertType advertType = getAdvertTypeByAdvertTypeId(advertTypeId);

        // Check if the advert type can be modified
        if (Boolean.TRUE.equals(advertType.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }

        // Check if there are related adverts
        if (advertRepository.existsByAdvertTypeId(advertTypeId)) {
            throw new BadRequestException(ErrorMessages.BAD_REQUEST_ADVERT_TYPE_TO_ADVERT);
        }

        advertTypeRepository.deleteById(advertType.getId());

        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_DELETE)
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(advertType))
                .httpStatus(HttpStatus.OK)
                .build();

    }

    /**
     * Retrieve an AdvertType by its ID.
     *
     * @param advertTypeId the ID of the AdvertType to be retrieved
     * @return the AdvertType object
     * @throws ResourceNotFoundException if the AdvertType is not found
     */
    private AdvertType getAdvertTypeByAdvertTypeId(Long advertTypeId) {
        return advertTypeRepository.findById(advertTypeId).
                orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_TYPE_MESSAGE, advertTypeId)));
    }


}

package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.AdvertType;
import com.project.warmyhomes.payload.mappers.AdvertTypeMapper;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.AdvertTypeRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertTypeResponse;
import com.project.warmyhomes.repository.business.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertTypeMapper advertTypeMapper;

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
}

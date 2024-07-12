package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.AdvertTypeRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertTypeResponse;
import com.project.warmyhomes.service.business.AdvertTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/advert-types")
@RequiredArgsConstructor
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    @PostMapping // http://localhost:8080/advert-types + POST + JSON
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<AdvertTypeResponse> createAdvertType(@Valid @RequestBody AdvertTypeRequest advertTypeRequest) {
        return advertTypeService.createAdvertType(advertTypeRequest);
    }
}

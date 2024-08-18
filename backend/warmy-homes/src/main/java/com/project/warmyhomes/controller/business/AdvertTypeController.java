package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.AdvertTypeRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertTypeResponse;
import com.project.warmyhomes.service.business.AdvertTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advert-types")
@RequiredArgsConstructor
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    @GetMapping // http://localhost:8080/advert-types/ + GET
    public ResponseEntity<List<AdvertTypeResponse>> getAllAdvertTypes() {
        return advertTypeService.getAllAdvertTypes();
    }

    @GetMapping("/{advertTypeId}") // http://localhost:8080/advert-types/:advertTypeId + GET
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<AdvertTypeResponse> getAdvertTypeById(@PathVariable Long advertTypeId) {
        return advertTypeService.getAdvertTypeById(advertTypeId);
    }

    @PostMapping // http://localhost:8080/advert-types + POST + JSON
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<AdvertTypeResponse> createAdvertType(@Valid @RequestBody AdvertTypeRequest advertTypeRequest) {
        return advertTypeService.createAdvertType(advertTypeRequest);
    }

    @PutMapping("/{advertTypeId}") // http://localhost:8080/advert-types/:advertTypeId + PUT + JSON
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<AdvertTypeResponse> updateAdvertType(@Valid @RequestBody AdvertTypeRequest advertTypeRequest, @PathVariable Long advertTypeId) {
        return advertTypeService.updateAdvertType(advertTypeRequest, advertTypeId);
    }

    @DeleteMapping("/{advertTypeId}") // http://localhost:8080/advert-types/:advertTypeId + DELETE
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<AdvertTypeResponse> deleteAdvertType(@PathVariable Long advertTypeId) {
        return advertTypeService.deleteAdvertType(advertTypeId);
    }
}

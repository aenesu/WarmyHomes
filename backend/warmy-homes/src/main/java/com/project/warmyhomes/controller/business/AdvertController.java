package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping //http://localhost:8080/adverts + POST + JSON
    @PreAuthorize("hasAnyAuthority('Customer')")
    public ResponseMessage<AdvertResponse> createAdvert(@Valid @RequestBody AdvertRequest advertRequest) {
        return advertService.createAdvert(advertRequest);
    }

    @GetMapping
    // http://localhost:8080/adverts?q=beyoğlu&category_id=12&advert_type_id=3&price_start=500&price_end=1500&status=1;page=1&size=10&sort=date&type=asc + GET
    public Page<AdvertResponse> getAllAdvertsByPage(
            @RequestParam(value = "q", defaultValue = " ") String query,
            @RequestParam(value = "category_id", defaultValue = "0") Long categoryId,
            @RequestParam(value = "advert_type_id", defaultValue = "0") Long advertTypeId,
            @RequestParam(value = "price_start", defaultValue = "0.0") BigDecimal priceStart,
            @RequestParam(value = "price_end", defaultValue = "0.0") BigDecimal priceEnd,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "startDate") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {

        return advertService.getAllAdvertsByPage(query, categoryId, advertTypeId, priceStart, priceEnd, status, page, size, sort, type);
    }
/*
    @GetMapping("/auth")  //localhost:8080/adverts/auth + GET
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Page<AdvertResponse> getUserAdvertsByPage(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "startDate") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {

        return advertService.getAllUserAdvertsByPage(request, page, size, sort, type);
    }

    @GetMapping("{/slugValue}")
    public ResponseEntity<AdvertResponse> getAdvertByName(@PathVariable String slugValue) {
        return ResponseEntity.ok(advertService.getAdvertByName(slugValue));
    }
    */
}

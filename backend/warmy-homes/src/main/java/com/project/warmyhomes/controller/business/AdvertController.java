package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    /*
    @GetMapping   //localhost:8080/adverts + GET
    public Page<AdvertResponse> getAdvertsByPage(
            @RequestParam(value = "q", defaultValue = " ") String q,
            @RequestParam(value = "category_id", defaultValue = "0") int category_id,
            @RequestParam(value = "advert_type_id", defaultValue = "") int advert_type_id,
            @RequestParam(value = "price_start", defaultValue = "0") int price_start,
            @RequestParam(value = "price_end", defaultValue = "0") int price_end,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "startDate") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {

        return advertService.getAllAdvertsByPage(q, category_id, advert_type_id, price_start, price_end, status, page, size, sort, type);
    }

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

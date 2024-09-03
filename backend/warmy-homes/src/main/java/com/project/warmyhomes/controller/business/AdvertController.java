package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertCategoriesResponse;
import com.project.warmyhomes.payload.response.business.AdvertCitiesResponse;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @GetMapping //http://localhost:8080/adverts?q=modern&category_id=1&advert_type_id=3&price_start=500000&price_end=1500000&status=0&page=0&size=10&sort=createDate&type=desc + GET
    public Page<AdvertResponse> getAllAdvertsByPage(
            @RequestParam(value = "q", defaultValue = "") String query,
            @RequestParam(value = "category_id", defaultValue = "0") Long categoryId,
            @RequestParam(value = "advert_type_id", defaultValue = "0") Long advertTypeId,
            @RequestParam(value = "price_start", defaultValue = "0.0") BigDecimal priceStart,
            @RequestParam(value = "price_end", defaultValue = "0.0") BigDecimal priceEnd,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {

        return advertService.getAllAdvertsByPage(query, categoryId, advertTypeId, priceStart, priceEnd, status, page, size, sort, type);
    }

    @GetMapping("/cities") //http://localhost:8080/adverts/cities + GET
    public ResponseEntity<List<AdvertCitiesResponse>> getAllAdvertsByCities(){
        return advertService.getAllAdvertsByCities();
    }

    @GetMapping("/categories") //http://localhost:8080/adverts/categories + GET
    public ResponseEntity<List<AdvertCategoriesResponse>> getAllAdvertsByCategories(){
        return advertService.getAllAdvertsByCategories();
    }

    @GetMapping("/popular") //http://localhost:8080/adverts/popular?amount=:amount + GET
    public ResponseEntity<List<AdvertResponse>> getPopularAdverts(@RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount){
        return advertService.getPopularAdverts(amount);
    }


    @GetMapping("/auth") //http://localhost:8080/adverts/auth?page=0&size=10&sort=createDate&type=desc + GET
    @PreAuthorize("hasAnyAuthority('Customer')")
    public Page<AdvertResponse> geUserAdvertsByPage(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {

        return advertService.getUserAdvertsByPage(request, page, size, sort, type);
    }

    @GetMapping("/admin") //http://localhost:8080/adverts/admin?q=modern&category_id=1&advert_type_id=3&price_start=500000&price_end=1500000&status=0&page=0&size=10&sort=createDate&type=desc + GET
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public Page<AdvertResponse> getAdvertsByPage(
            @RequestParam(value = "q", defaultValue = "") String query,
            @RequestParam(value = "category_id", defaultValue = "0") Long categoryId,
            @RequestParam(value = "advert_type_id", defaultValue = "0") Long advertTypeId,
            @RequestParam(value = "price_start", defaultValue = "0.0") BigDecimal priceStart,
            @RequestParam(value = "price_end", defaultValue = "0.0") BigDecimal priceEnd,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {

        return advertService.getAdvertsByPage(query, categoryId, advertTypeId, priceStart, priceEnd, status, page, size, sort, type);
    }

    @GetMapping("{/slug}") //http://localhost:8080/adverts/:slug + GET
    public ResponseMessage<AdvertResponse> getAdvertBySlug(@PathVariable String slug) {
        return advertService.getAdvertBySlug(slug);
    }



   // /adverts/:id/auth + GET
   // /adverts/:id/admin/adverts/:id/admin + GET

    @PostMapping //http://localhost:8080/adverts + POST + JSON
    @PreAuthorize("hasAnyAuthority('Customer')")
    public ResponseMessage<AdvertResponse> createAdvert(@Valid @RequestBody AdvertRequest advertRequest) {
        return advertService.createAdvert(advertRequest);
    }

    // /adverts/auth/:id + PUT
    // /adverts/admin/:id + PUT
    // /adverts/admin/:id + DELETE
}

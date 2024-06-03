package com.project.warmyhomes.service.business;

import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;

    /*
    public Page<AdvertResponse> getAllAdvertsByPage(String q, int categoryId, int advertTypeId, int priceStart, int priceEnd, int status, int page, int size, String sort, String type) {

        return null;
    }

    public Page<AdvertResponse> getAllUserAdvertsByPage(HttpServletRequest request, int page, int size, String sort, String type) {
       String email = (String) request.getAttribute("email");
       return null;
    }

    public AdvertResponse getAdvertByName(String slugValue) {
        return advertRepository.getAdvertByName(slugValue);
    }
    */
}

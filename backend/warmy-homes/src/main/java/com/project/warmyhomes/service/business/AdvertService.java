package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.payload.mappers.AdvertMapper;
import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.repository.business.AdvertRepository;
import com.project.warmyhomes.service.helper.MethodHelper;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    private final MethodHelper methodHelper;
    private final PageableHelper pageableHelper;

    /**
     * Create a new Advert based on the provided AdvertRequest.
     *
     * @param advertRequest the request object containing the details for the Advert to be created
     * @return a ResponseMessage containing the created AdvertResponse, success message, and HTTP status
     */
    public ResponseMessage<AdvertResponse> createAdvert(AdvertRequest advertRequest) {
        return null;
    }

    /**
     * Retrieve a paginated list of advertisements based on the provided filtering and sorting criteria.
     * <p>
     * This method allows for the retrieval of advertisements with optional filtering parameters such as
     * search query, category ID, advertisement type ID, price range, and status. The results are paginated
     * and can be sorted based on specified fields.
     *
     * @param query        the search query to filter advertisements by title or description (optional).
     * @param categoryId   the ID of the category to filter advertisements by (optional).
     * @param advertTypeId the ID of the advertisement type to filter by (optional).
     * @param priceStart   the minimum price to filter advertisements by (optional).
     * @param priceEnd     the maximum price to filter advertisements by (optional).
     * @param status       the status of the advertisements to filter by (optional).
     * @param page         the page number to retrieve (0-indexed).
     * @param size         the number of items per page.
     * @param sort         the field by which to sort the results (e.g., "price", "createdDate").
     * @param type         the sort direction, either "asc" for ascending or "desc" for descending.
     */
    public Page<AdvertResponse> getAllAdvertsByPage(String query, Long categoryId, Long advertTypeId, int priceStart, int priceEnd, int status, int page, int size, String sort, String type) {

        // Create pageable object with sorting parameters
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (query != null) {
            return advertRepository.findAdvertByQuery(query, categoryId, advertTypeId, priceStart, priceEnd, status, pageable)
                    .map(advertMapper::advertToAdvertResponse);
        } else {
            return advertRepository.findAll(pageable)
                    .map(advertMapper::advertToAdvertResponse);
        }
    }

/*
    public Page<AdvertResponse> getAllUserAdvertsByPage(HttpServletRequest request, int page, int size, String sort, String type) {
       String email = (String) request.getAttribute("email");
       return null;
    }

    public AdvertResponse getAdvertByName(String slugValue) {
        return advertRepository.getAdvertByName(slugValue);
    }
     */
}

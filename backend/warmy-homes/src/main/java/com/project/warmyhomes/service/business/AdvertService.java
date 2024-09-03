package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.*;
import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.AdvertMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.AdvertImageRequest;
import com.project.warmyhomes.payload.request.business.AdvertPropertyRequest;
import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.AdvertCategoriesResponse;
import com.project.warmyhomes.payload.response.business.AdvertCitiesResponse;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.repository.business.*;
import com.project.warmyhomes.service.helper.MethodHelper;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    private final MethodHelper methodHelper;
    private final PageableHelper pageableHelper;

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    private final ImageRepository imageRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;

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
    public Page<AdvertResponse> getAllAdvertsByPage(String query, Long categoryId, Long advertTypeId, BigDecimal priceStart, BigDecimal priceEnd, int status, int page, int size, String sort, String type) {

        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (!query.trim().isEmpty()) {
            return advertRepository.findAdvertByQuery(query, categoryId, advertTypeId, priceStart, priceEnd, status, pageable)
                    .map(advertMapper::mapAdvertToAdvertResponse);
        } else {
            return advertRepository.findAll(pageable)
                    .map(advertMapper::mapAdvertToAdvertResponse);
        }
    }

    /**
     * Retrieve a list of cities along with the count of advertisements for each city.
     * The data is fetched from the repository, mapped to response objects, and returned
     * as a structured response with HTTP status 200 OK.
     *
     * @return ResponseEntity containing a list of AdvertCitiesResponse objects with city names
     * and advertisement counts.
     */
    public ResponseEntity<List<AdvertCitiesResponse>> getAllAdvertsByCities() {
        List<Object[]> result = advertRepository.findAdvertsGroupedByCities();

        List<AdvertCitiesResponse> advertCitiesResponseList = result.stream()
                .map(advertMapper::mapAdvertToAdvertCitiesResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(advertCitiesResponseList);
    }

    /**
     * Retrieve a list of advertisement categories along with the count of advertisements for each category.
     * The data is fetched from the repository, mapped to response objects, and returned as a structured response
     * with HTTP status 200 OK.
     *
     * @return ResponseEntity containing a list of AdvertCategoriesResponse objects with category names
     * and advertisement counts.
     */
    public ResponseEntity<List<AdvertCategoriesResponse>> getAllAdvertsByCategories() {
        List<Object[]> result = advertRepository.findAdvertsGroupedByCategories();

        List<AdvertCategoriesResponse> advertCategoriesResponseList = result.stream()
                .map(advertMapper::mapAdvertToAdvertCategoriesResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(advertCategoriesResponseList);
    }

    /**
     * Retrieve a list of popular advertisements, ordered by a custom popularity metric.
     * The popularity is determined based on a combination of factors such as tour requests and view count.
     * If no specific amount is provided, the method will return the default number of popular advertisements.
     *
     * @param amount the number of top popular advertisements to retrieve. If null, a default value is used.
     * @return ResponseEntity containing a list of AdvertResponse objects representing the most popular advertisements
     */
    public ResponseEntity<List<AdvertResponse>> getPopularAdverts(Integer amount) {
        List<AdvertResponse> advertResponseList = advertRepository.findTopPopularAdverts(amount)
                .stream()
                .map(advertMapper::mapAdvertToAdvertResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(advertResponseList);
    }

    /**
     * Retrieve a paginated list of adverts created by the user associated with the provided request.
     * <p>
     * This method uses the provided pagination and sorting parameters to fetch adverts from the
     * repository based on the user's ID, which is determined by their email address from the request.
     * The adverts are then mapped to a list of {@link AdvertResponse} objects.
     *
     * @param request The HTTP request containing the user's email address.
     * @param page    The page number to retrieve (0-based index).
     * @param size    The number of adverts to retrieve per page.
     * @param sort    The field by which to sort the adverts.
     * @param type    The sort direction, either 'asc' for ascending or 'desc' for descending.
     * @return A {@link Page} of {@link AdvertResponse} objects representing the user's adverts.
     */
    public Page<AdvertResponse> getUserAdvertsByPage(HttpServletRequest request, int page, int size, String sort, String type) {
        User user = methodHelper.isUserExistByEmail(request);

        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        return advertRepository.findByUserId(user.getId(), pageable)
                .map(advertMapper::mapAdvertToAdvertResponse);

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
    public Page<AdvertResponse> getAdvertsByPage(String query, Long categoryId, Long advertTypeId, BigDecimal priceStart, BigDecimal priceEnd, int status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (!query.trim().isEmpty()) {
            return advertRepository.findAdvertByQuery(query, categoryId, advertTypeId, priceStart, priceEnd, status, pageable)
                    .map(advertMapper::mapAdvertToAdvertResponse);
        } else {
            return advertRepository.findAll(pageable)
                    .map(advertMapper::mapAdvertToAdvertResponse);
        }
    }


    public ResponseMessage<AdvertResponse> getAdvertBySlug(String slug) {
        Advert advert = advertRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_WITH_SLUG, slug)));

        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_FOUND)
                .object(advertMapper.mapAdvertToAdvertResponse(advert))
                .httpStatus(HttpStatus.OK)
                .build();
    }



    /**
     * Create a new Advert based on the provided AdvertRequest.
     * <p>
     * This method maps the details from the given AdvertRequest to a new Advert entity, set its attributes including
     * slug, type, and related entities (Country, City, District, User, and Category), and saves it to the database.
     * It also processes and saves associated images and property values if they are provided.
     * The method return a ResponseMessage containing the created AdvertResponse, a success message, and the HTTP status.
     *
     * @param advertRequest the request object containing the details for the Advert to be created
     * @return a ResponseMessage containing the created AdvertResponse, a success message, and HTTP status
     * @throws ResourceNotFoundException if any of the related entities (Country, City, District) are not found
     */
    public ResponseMessage<AdvertResponse> createAdvert(AdvertRequest advertRequest) {
        Advert advert = advertMapper.mapAdvertRequestToAdvert(advertRequest);

        String slug = generateSlug(advertRequest.getTitle());
        advert.setSlug(slug);

        AdvertType advertType = methodHelper.isAdvertTypeExistById(advertRequest.getAdvertTypeId());
        advert.setAdvertType(advertType);

        Country country = countryRepository.findById(advertRequest.getCountryId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_COUNTRY, advertRequest.getCountryId()))
        );
        advert.setCountry(country);

        City city = cityRepository.findById(advertRequest.getCityId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CITY, advertRequest.getCityId()))
        );
        advert.setCity(city);

        District district = districtRepository.findById(advertRequest.getDistrictId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_DISTRICT, advertRequest.getCityId()))
        );
        advert.setDistrict(district);

        User user = methodHelper.isUserExist(advertRequest.getUserId());
        advert.setUser(user);

        Category category = methodHelper.isCategoryExistById(advertRequest.getCategoryId());
        advert.setCategory(category);

        Advert createdAdvert = advertRepository.save(advert);

        List<Image> advertImageList = new ArrayList<>();

        if (advertRequest.getImages() != null && !advertRequest.getImages().isEmpty()) {
            for (AdvertImageRequest imageRequest : advertRequest.getImages()) {
                Image image = Image.builder()
                        .name(imageRequest.getName())
                        .type(imageRequest.getType())
                        .data(imageRequest.getData().getBytes())
                        .featured(imageRequest.getFeatured())
                        .advert(createdAdvert)
                        .build();
                Image uploadImage = imageRepository.save(image);
                if (Boolean.TRUE.equals(uploadImage.getFeatured())) {
                    imageRepository.findFeaturedImageByAdvertId(uploadImage.getAdvert().getId());
                }
                advertImageList.add(uploadImage);
            }
            createdAdvert.setImages(advertImageList);
        }

        List<CategoryPropertyValue> propertyValueList = new ArrayList<>();

        if (advertRequest.getProperties() != null && !advertRequest.getProperties().isEmpty()) {
            for (AdvertPropertyRequest propertyRequest : advertRequest.getProperties()) {
                // Find the CategoryPropertyKey by ID
                CategoryPropertyKey categoryPropertyKey = methodHelper.isCategoryPropertyKeyExistById(propertyRequest.getKeyId());

                // Create a new CategoryPropertyValue and set its fields
                CategoryPropertyValue categoryPropertyValue = CategoryPropertyValue.builder()
                        .propertyKey(categoryPropertyKey)
                        .value(propertyRequest.getValue())
                        .advert(createdAdvert)
                        .build();

                // Save the CategoryPropertyValue
                CategoryPropertyValue createdPropertyValue = categoryPropertyValueRepository.save(categoryPropertyValue);
                propertyValueList.add(createdPropertyValue);
            }
            createdAdvert.setPropertyValues(propertyValueList);
        }

        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_CREATE)
                .object(advertMapper.mapAdvertToAdvertResponse(createdAdvert))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    /**
     * Generate a URL-friendly slug from the given title.
     * <p>
     * This method transforms the input title into a lowercase slug suitable for use in URLs. It replaces specific Turkish characters
     * with their English equivalents, removes any characters that are not alphanumeric or spaces, and converts multiple spaces into
     * single hyphens. The resulting slug is trimmed of any leading or trailing spaces.
     *
     * @param title the input string from which the slug will be generated
     * @return a URL-friendly slug derived from the input title
     */
    private String generateSlug(String title) {

        return title.toLowerCase(Locale.forLanguageTag("us"))
                .replace("ğ", "g")
                .replace("ü", "u")
                .replace("ş", "s")
                .replace("ı", "i")
                .replace("ö", "o")
                .replace("ç", "c")
                .replaceAll("[^a-z0-9 ]", "")
                .replaceAll("\\s+", "-")
                .trim();
    }

}

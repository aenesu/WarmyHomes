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
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.repository.business.*;
import com.project.warmyhomes.service.helper.MethodHelper;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        // Create pageable object with sorting parameters
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (query != null) {
            return advertRepository.findAdvertByQuery(query, categoryId, advertTypeId, priceStart, priceEnd, status, pageable)
                    .map(advertMapper::mapAdvertToAdvertResponse);
        } else {
            return advertRepository.findAll(pageable)
                    .map(advertMapper::mapAdvertToAdvertResponse);
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

package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.City;
import com.project.warmyhomes.entity.concretes.business.Country;
import com.project.warmyhomes.entity.concretes.business.District;
import com.project.warmyhomes.payload.response.business.CityResponse;
import com.project.warmyhomes.payload.response.business.CountryResponse;
import com.project.warmyhomes.payload.response.business.DistrictResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    /**
     *
     * @param country from DB
     * @return DistrictResponse DTO
     */
    public CountryResponse mapCountryToCountryResponse(Country country) {
        return CountryResponse.builder()
                .countryId(country.getId())
                .name(country.getName())
                .build();
    }

    /**
     *
     * @param city from DB
     * @return DistrictResponse DTO
     */
    public CityResponse mapCityToCityResponse(City city) {
        return CityResponse.builder()
                .cityId(city.getId())
                .name(city.getName())
                .country(city.getCountry())
                .build();
    }

    /**
     *
     * @param district from DB
     * @return DistrictResponse DTO
     */
    public DistrictResponse mapDistrictToDistrictResponse(District district) {
        return DistrictResponse.builder()
                .districtId(district.getId())
                .name(district.getName())
                .city(district.getCity())
                .build();
    }
}

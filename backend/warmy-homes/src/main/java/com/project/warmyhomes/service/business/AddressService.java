package com.project.warmyhomes.service.business;

import com.project.warmyhomes.payload.mappers.AddressMapper;
import com.project.warmyhomes.payload.response.business.CityResponse;
import com.project.warmyhomes.payload.response.business.CountryResponse;
import com.project.warmyhomes.payload.response.business.DistrictResponse;
import com.project.warmyhomes.repository.business.CityRepository;
import com.project.warmyhomes.repository.business.CountryRepository;
import com.project.warmyhomes.repository.business.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    public final AddressMapper addressMapper;

    public final CountryRepository countryRepository;
    public final CityRepository cityRepository;
    public final DistrictRepository districtRepository;

    /**
     * Retrieve all countries from the repository and maps them to CountryResponse objects.
     *
     * @return a list of all CountryResponse objects
     */
    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(addressMapper::mapCountryToCountryResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all cities from the repository and maps them to CityResponse objects.
     *
     * @return a list of all CityResponse objects
     */
    public List<CityResponse> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(addressMapper::mapCityToCityResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all districts from the repository and maps them to DistrictResponse objects.
     *
     * @return a list of all DistrictResponse objects
     */
    public List<DistrictResponse> getAllDistricts() {
        return districtRepository.findAll()
                .stream()
                .map(addressMapper::mapDistrictToDistrictResponse)
                .collect(Collectors.toList());
    }
}
package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.response.business.CityResponse;
import com.project.warmyhomes.payload.response.business.CountryResponse;
import com.project.warmyhomes.payload.response.business.DistrictResponse;
import com.project.warmyhomes.service.business.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/countries") // http://localhost:8080/address/countries + GET
    public List<CountryResponse> getAllCountries() {
        return addressService.getAllCountries();
    }

    @GetMapping("/cities") // http://localhost:8080/address/cities + GET
    public List<CityResponse> getAllCities() {
        return addressService.getAllCities();
    }

    @GetMapping("/districts") // http://localhost:8080/address/districts + GET
    public List<DistrictResponse> getAllDistricts() {
        return addressService.getAllDistricts();
    }
}

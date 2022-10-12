package ru.jigulin.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jigulin.weather.dto.city.CityResponse;
import ru.jigulin.weather.dto.city.CityUpdateRequestResponse;
import ru.jigulin.weather.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityResponse> readAllCities() {
        return cityService.getAllCities();
    }

    @PutMapping("/{id}")
    public CityUpdateRequestResponse updateCity(
            @PathVariable Long id,
            @RequestBody CityUpdateRequestResponse request
    ) {
        return cityService.updateCity(id, request);
    }

    @PutMapping("/{id}/{temperature}")
    public Integer updateCityWeather(@PathVariable Long id, @PathVariable Integer temperature) {
        return cityService.updateCityWeather(id, temperature);
    }
}

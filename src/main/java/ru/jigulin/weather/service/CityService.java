package ru.jigulin.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jigulin.weather.dto.city.CityResponse;
import ru.jigulin.weather.dto.city.CityUpdateRequestResponse;
import ru.jigulin.weather.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream().map(CityResponse::from).collect(Collectors.toList());
    }

    public Integer updateCityWeather(Long id, Integer temperature) {
        var changed = false;
        var city = cityRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id = " + id));
        if (temperature != null && temperature.intValue() != city.getWeather().getTemperature().intValue()) {
            changed = true;
            city.getWeather().setTemperature(temperature);
        }

        if(changed) {
            city = cityRepository.save(city);
        }

        return city.getWeather().getTemperature();
    }

    public CityUpdateRequestResponse updateCity(Long id, CityUpdateRequestResponse request) {
        var changed = false;
        var city = cityRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id = " + id));

        if (request != null) {
            if (
                    request.getName() != null
                    && !request.getName().isBlank()
                    && !request.getName().equals(city.getName())
            ) {
                changed = true;
                city.setName(request.getName());
            }

            if (
                    request.getSubscribable() != null
                    && request.getSubscribable().booleanValue() != city.getSubscribable().booleanValue()
            ) {
                changed = true;
                city.setSubscribable(request.getSubscribable());
                if (!request.getSubscribable()) {
                    city.getSubscribers().clear();
                }
            }
        }

        if (changed) {
            city = cityRepository.save(city);
        }

        return CityUpdateRequestResponse.from(city);
    }
}

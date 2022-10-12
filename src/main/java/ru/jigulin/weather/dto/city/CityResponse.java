package ru.jigulin.weather.dto.city;

import ru.jigulin.weather.entity.City;

public class CityResponse {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CityResponse from(City city) {
        var resp = new CityResponse();

        if (city != null) {
            resp.setId(city.getId());
            resp.setName(city.getName());
        }

        return resp;
    }
}

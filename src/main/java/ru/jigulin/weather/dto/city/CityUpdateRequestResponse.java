package ru.jigulin.weather.dto.city;

import ru.jigulin.weather.entity.City;

public class CityUpdateRequestResponse {
    private String name;
    private Boolean subscribable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSubscribable() {
        return subscribable;
    }

    public void setSubscribable(Boolean subscribable) {
        this.subscribable = subscribable;
    }

    public static CityUpdateRequestResponse from(City city) {
        var resp = new CityUpdateRequestResponse();

        if (city != null) {
            resp.setName(city.getName());
            resp.setSubscribable(city.getSubscribable());
        }

        return resp;
    }
}

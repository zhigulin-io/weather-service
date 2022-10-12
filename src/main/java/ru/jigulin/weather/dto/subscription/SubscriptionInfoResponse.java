package ru.jigulin.weather.dto.subscription;

import ru.jigulin.weather.entity.City;

public class SubscriptionInfoResponse {
    private String cityName;
    private Integer temperature;

    public static SubscriptionInfoResponse from(City city) {
        var response = new SubscriptionInfoResponse();

        if (city != null) {
            response.setCityName(city.getName());
            if (city.getWeather() != null) {
                response.setTemperature(city.getWeather().getTemperature());
            }
        }

        return response;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}

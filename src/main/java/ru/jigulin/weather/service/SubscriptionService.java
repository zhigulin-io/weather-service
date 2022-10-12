package ru.jigulin.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jigulin.weather.dto.subscription.SubscriptionInfoResponse;
import ru.jigulin.weather.repository.CityRepository;
import ru.jigulin.weather.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public SubscriptionService(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    public List<SubscriptionInfoResponse> getInfoFromSubscription(String username) {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found"));

        return user.getSubscriptions().stream().map(SubscriptionInfoResponse::from).collect(Collectors.toList());
    }

    public String subscribe(String username, String cityName) {
        String response = null;

        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found"));

        var city = cityRepository
                .findByName(cityName)
                .orElseThrow(() -> new RuntimeException("City is not found with name = " + cityName));

        if (!user.getSubscriptions().contains(city) && city.getSubscribable()) {
            user.getSubscriptions().add(city);
            user = userRepository.save(user);
            if (user.getSubscriptions().contains(city)) {
                response = city.getName();
            }
        }

        return response;
    }
}

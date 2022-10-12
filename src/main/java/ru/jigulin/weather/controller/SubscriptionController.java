package ru.jigulin.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.jigulin.weather.dto.subscription.SubscriptionInfoResponse;
import ru.jigulin.weather.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<SubscriptionInfoResponse> readAllWeathers() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return subscriptionService.getInfoFromSubscription(username);
    }

    @PostMapping
    public String subscribe(@RequestBody String cityName) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return subscriptionService.subscribe(username, cityName);
    }
}

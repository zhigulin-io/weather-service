package ru.jigulin.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jigulin.weather.dto.user.UserResponse;
import ru.jigulin.weather.dto.user.UserUpdateRequestResponse;
import ru.jigulin.weather.dto.user.UserWithSubsResponse;
import ru.jigulin.weather.entity.User;
import ru.jigulin.weather.repository.UserRepository;
import ru.jigulin.weather.security.UserDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"))
        );
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
    }

    public UserWithSubsResponse getUserWithSubscriptions(long id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + id));
        user.getSubscriptions();
        return UserWithSubsResponse.from(user);
    }

    public UserUpdateRequestResponse updateUser(Long id, UserUpdateRequestResponse req) {
        var changed = false;
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id = " + id));
        if (req != null) {
            if (
                    req.getUsername() != null
                    && !req.getUsername().isBlank()
                    && !req.getUsername().equals(user.getUsername())
            ) {
                changed = true;
                user.setUsername(req.getUsername());
            }

            if (
                    req.getEmptySubs() != null
                    && req.getEmptySubs()
                    && !user.getSubscriptions().isEmpty()
            ) {
                changed = true;
                user.getSubscriptions().clear();
            }
        }

        if (changed) {
            user = userRepository.save(user);
        }

        return UserUpdateRequestResponse.from(user);
    }
}

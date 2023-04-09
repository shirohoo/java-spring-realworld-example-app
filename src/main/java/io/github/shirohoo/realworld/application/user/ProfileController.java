package io.github.shirohoo.realworld.application.user;

import static org.springframework.http.HttpStatus.CREATED;

import io.github.shirohoo.realworld.domain.user.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/api/profiles/{username}")
    public ProfileResponse getProfile(User me, @PathVariable("username") String to) {
        return profileService.getProfile(me, to);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/api/profiles/{username}/follow")
    public ProfileResponse follow(User me, @PathVariable("username") String to) {
        return profileService.follow(me, to);
    }

    @DeleteMapping("/api/profiles/{username}/follow")
    public ProfileResponse unfollow(User me, @PathVariable("username") String to) {
        return profileService.unfollow(me, to);
    }
}
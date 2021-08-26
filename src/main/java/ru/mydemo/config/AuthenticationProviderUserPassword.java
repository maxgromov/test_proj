package ru.mydemo.config;


import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import ru.mydemo.service.UserService;


@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {
    private final UserService userService;

    public AuthenticationProviderUserPassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Mono.<AuthenticationResponse>create(emitter -> {
            if (userService.auth(authenticationRequest.getIdentity().toString(), authenticationRequest.getSecret().toString())){
                emitter.success(AuthenticationResponse.success(authenticationRequest.getIdentity().toString()));
            }

            else {
                emitter.error(AuthenticationResponse.exception());
            }
        });
    }
}
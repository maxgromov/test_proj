package ru.mydemo.config;


import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import ru.mydemo.service.UserService;
import ru.mydemo.tables.records.UsersRecord;

import java.util.Map;


@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {
    private final UserService userService;

    @Inject
    public AuthenticationProviderUserPassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        UsersRecord auth = userService.auth(authenticationRequest.getIdentity().toString(), authenticationRequest.getSecret().toString());
        return Mono.<AuthenticationResponse>create(emitter -> {
            if (auth!=null){
                emitter.success(
                        AuthenticationResponse.success(auth.getName(), Map.of("id", auth.getId()))
                );
            }

            else {
                emitter.error(AuthenticationResponse.exception());
            }
        });
    }
}
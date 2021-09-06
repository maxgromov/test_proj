package ru.mydemo.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;


@Singleton
public class HelloDataFetcher implements DataFetcher<String> {
    private final SecurityService securityService;

    @Inject
    public HelloDataFetcher(SecurityService service) {
        this.securityService = service;
    }

    @Override
    public String get(DataFetchingEnvironment environment) throws Exception {

        Optional<Authentication> authentication = securityService.getAuthentication();
        if (authentication.isPresent()){
            Long userId = (Long) authentication.get().getAttributes().get("id");
            return "UserID " + userId;
        }

        return "World!";
    }
}

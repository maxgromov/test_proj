package ru.mydemo.graphql.mutation;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ru.mydemo.dto.UserData;
import ru.mydemo.repository.UsersRepository;
import ru.mydemo.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserCreateMutationDataFetcher implements DataFetcher<UserData> {
    private final UserService userService;

    @Inject
    public UserCreateMutationDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserData get(DataFetchingEnvironment environment) throws Exception {
        String name = environment.getArgument("name").toString();
        return userService.create(name);
    }
}

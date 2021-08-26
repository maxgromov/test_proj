package ru.mydemo.graphql.mutation;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.mydemo.dto.UserData;
import ru.mydemo.repository.UsersRepository;
import ru.mydemo.service.UserService;



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
        String password = environment.getArgument("password").toString();
        return userService.create(name, password);
    }
}

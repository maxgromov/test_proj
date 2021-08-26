package ru.mydemo.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.mydemo.dto.UserDTO;
import ru.mydemo.repository.UsersRepository;



@Singleton
public class FindUserDataFetcher implements DataFetcher<UserDTO> {
    private final UsersRepository usersRepository;

    @Inject
    public FindUserDataFetcher(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDTO get(DataFetchingEnvironment environment) throws Exception {
        Integer id = (Integer) environment.getArgument("id") ;
        return new UserDTO(usersRepository.findUserByID(id));
    }
}

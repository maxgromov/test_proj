package ru.mydemo.service;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import ru.mydemo.dto.UserDTO;
import ru.mydemo.dto.UserData;
import ru.mydemo.repository.UsersRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {
    private final UsersRepository usersRepository;

    @Inject
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserData create(String name) {
        if (usersRepository.userExist(name)){

            return new UserData("User "+ name +" is already exist");
        }
        return new UserData(new UserDTO(usersRepository.createUser(name)));
    }
}

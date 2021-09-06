package ru.mydemo.service;

import com.google.common.hash.Hashing;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.mydemo.dto.UserDTO;
import ru.mydemo.dto.UserData;
import ru.mydemo.repository.UsersRepository;
import ru.mydemo.tables.records.UsersRecord;

import java.nio.charset.StandardCharsets;


@Singleton
public class UserService {
    private final UsersRepository usersRepository;

    @Inject
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserData create(String name, String password) {
        if (usersRepository.userExist(name)){

            return new UserData("User "+ name +" is already exist");
        }
        password = password.trim();
        return new UserData(new UserDTO(usersRepository.createUser(name, encrypt(password))));
    }

    public UsersRecord auth(String name, String password){
        password = password.trim();
        return  usersRepository.findUserByNameAndPassword(name, encrypt(password));

    }

    private String encrypt(String password){
        password = password.trim();
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

    }
}

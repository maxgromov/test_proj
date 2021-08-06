package ru.mydemo;

import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import ru.mydemo.dto.UserDTO;
import ru.mydemo.repository.UsersRepository;
import ru.mydemo.tables.records.UsersRecord;

import javax.inject.Inject;

@Controller("/user")
public class UserController {
    private final UsersRepository usersRepository;

    @Inject
    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Get(value = "/find",
            produces = MediaType.TEXT_PLAIN)
//    @Cacheable("users-cache")
//    public HttpResponse<String> get(@QueryValue Integer id) {
//        String user = usersRepository.findUserByID(id);
//        if (user ==null){
//            return HttpResponse.notFound();
//        }
//        return HttpResponse.ok(user);
//    }
    public String get(@QueryValue Integer id) {
        return usersRepository.findUserByID(id);
    }

//    consumes -  что мы хотим получить
//    produces - что мы производим
    @Post(value = "/create",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<UserDTO> create(String name){
        if (usersRepository.userExist(name)){
            return HttpResponse.status(
                    HttpStatus.BAD_REQUEST,
                    "User "+ name +" is already exist");
        }
        return HttpResponse.ok(
                new UserDTO(usersRepository.
                createUser(name)));
    }
    @Post(value = "/delete",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> delete(String name){
        if (usersRepository.userExist(name)){
            usersRepository.deleteUser(name);
            return HttpResponse.ok();
        }
        return HttpResponse.status(
                HttpStatus.BAD_REQUEST,
                "User "+ name +" is not exist");
    }

}

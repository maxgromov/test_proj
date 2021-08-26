package ru.mydemo;

import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.jooq.Record2;
import ru.mydemo.dto.UserCreateInput;
import ru.mydemo.dto.UserDTO;
import ru.mydemo.dto.UserData;
import ru.mydemo.repository.UsersRepository;
import ru.mydemo.service.UserService;


import java.util.List;

@Controller("/user")
public class UserController {
    private final UsersRepository usersRepository;
    private final UserService userService;

    @Inject
    public UserController(UsersRepository usersRepository, UserService userService) {
        this.usersRepository = usersRepository;
        this.userService = userService;
    }


    @Get(value = "/find",
            produces = MediaType.TEXT_PLAIN)
    @Cacheable("users-cache")
//    public HttpResponse<String> get(@QueryValue Integer id) {
//        UsersRecord user = usersRepository.findUserByID(id);
//        if (user ==null){
//            return HttpResponse.notFound("");
//        }
//        return HttpResponse.ok(user.getName());
//    }
    public String get(@QueryValue Integer id) {
        return usersRepository.findUserByID(id).getName();
    }
    @Get(value = "/getTable",
            produces = MediaType.TEXT_PLAIN)
    public List<Record2<Integer, String>> get() {
        return usersRepository.getUsersTable();
    }
//    consumes -  что мы хотим получить
//    produces - что мы производим


    @Post(value = "/create",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
//    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<UserDTO> create(UserCreateInput input){
        UserData userData = userService.create(input.getName(), input.getPassword());
        if (userData.getError()!=null){
            return HttpResponse.status(HttpStatus.BAD_REQUEST, "User "+ input.getName() +" is already exist");
        }
        return HttpResponse.ok(userData.getUser());

//Переопределили метод с помощью сервисного слоя (можно возвращать ошибку и самого юзера без проблем)
//        if (usersRepository.userExist(name)){
//            return HttpResponse.status(
//                    HttpStatus.BAD_REQUEST,
//                    "User "+ name +" is already exist");
//        }
//        return HttpResponse.ok(
//                new UserDTO(usersRepository.
//                createUser(name)));
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

package ru.mydemo;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UserControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void getSuccess() {
        String result = httpClient.toBlocking().
                retrieve(HttpRequest.GET("/user/find?id=" + 4), String.class);
        assertEquals("User 4",
                result
        );
    }

//    @Test
//    void create() {
//    }
//
//    @Test
//    void delete() {
//    }
}
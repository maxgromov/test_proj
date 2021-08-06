package ru.mydemo.repository;
import io.micronaut.cache.annotation.Cacheable;
import org.jooq.DSLContext;
import ru.mydemo.tables.Users;
import ru.mydemo.tables.records.UsersRecord;

import javax.inject.Inject;
import java.util.List;


public class UsersRepository {
    private final DSLContext dslContext;

    @Inject
    public UsersRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

//    @Cacheable("users-cache")
    public String findUserByID(Integer id){
        return dslContext.select(Users.USERS.NAME).
                from(Users.USERS).
                where(Users.USERS.ID.eq(id)).
                fetchAnyInto(String.class);
    }
    public UsersRecord createUser(String name){
        return dslContext.insertInto(Users.USERS)
                .columns(Users.USERS.NAME)
                .values(name)
                .returning()
                .fetchOne();
    }
    public boolean userExist(String name){
        return 0 != dslContext.select().
                from(Users.USERS).
                where(Users.USERS.NAME.equalIgnoreCase(name)).
                execute();
    }

    public void deleteUser(String name){
                dslContext.
                delete(Users.USERS).
                where(Users.USERS.NAME.eq(name)).
                execute();
    }

}

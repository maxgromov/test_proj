package ru.mydemo.repository;
import io.micronaut.cache.annotation.Cacheable;
import org.jooq.DSLContext;
import org.jooq.Record2;
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
    public UsersRecord findUserByID(Integer id){
        return dslContext.select(Users.USERS.NAME, Users.USERS.ID).
                from(Users.USERS).
                where(Users.USERS.ID.eq(id)).
                fetchOneInto(UsersRecord.class);
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

    public List<Record2<Integer, String>> getUsersTable(){
        return dslContext.select(Users.USERS.ID,  Users.USERS.NAME)
                .from(Users.USERS)
                .fetch();

    }

}

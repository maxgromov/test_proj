package ru.mydemo.dto;

import ru.mydemo.tables.records.UsersRecord;

public class UserDTO {
    private String name;
    private Integer id;
    private String password;


    public UserDTO(){}

    public UserDTO(UsersRecord usersRecord){
        this.id = usersRecord.getId();
        this.name = usersRecord.getName();
        this.password = usersRecord.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

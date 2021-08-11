package ru.mydemo.dto;


public class UserData {
    private UserDTO user;
    private String error;

    public UserData() {}

    public UserData(UserDTO user) {
        this.user = user;
    }

    public UserData(String error) {
        this.error = error;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}



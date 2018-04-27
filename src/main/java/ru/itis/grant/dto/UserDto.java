package ru.itis.grant.dto;

public class UserDto implements Data {
    private long id;

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

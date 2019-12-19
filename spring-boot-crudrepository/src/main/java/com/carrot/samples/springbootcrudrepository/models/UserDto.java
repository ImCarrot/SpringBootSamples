package com.carrot.samples.springbootcrudrepository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

public class UserDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("pets")
    private Collection<String> pets;

    public UserDto(UserDao context) {
        this.userId = context.getUserId();
        this.username = context.getUsername();
        this.firstName = context.getFirstName();
        this.lastName = context.getLastName();
        this.age = context.getAge();
        this.pets = context.getPets() == null ? new HashSet<>() : new HashSet<>(context.getPets());
    }

    public UserDto() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Collection<String> getPets() {
        return pets;
    }
}

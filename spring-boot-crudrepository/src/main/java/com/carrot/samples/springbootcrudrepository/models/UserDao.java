package com.carrot.samples.springbootcrudrepository.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class UserDao {

    @Id
    private String userId;

    @Indexed(unique = true)
    @Field("username")
    private String username;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("age")
    private int age;

    @Field("pets")
    private Set<String> pets;

    public UserDao() {
    }

    public UserDao(UserDto context) {
        this.userId = context.getUserId();
        this.username = context.getUsername();
        this.firstName = context.getFirstName();
        this.lastName = context.getLastName();
        this.age = context.getAge();
        this.pets = context.getPets() == null ? new HashSet<>() : new HashSet<>(context.getPets());
    }

    public UserDao updatedFrom(UserDto context) {

        if (context.getUsername() != null && !context.getUsername().trim().isEmpty())
            this.username = context.getUsername();

        if (context.getUsername() != null && !context.getUsername().trim().isEmpty())
            this.firstName = context.getFirstName();

        if (context.getUsername() != null && !context.getUsername().trim().isEmpty())
            this.lastName = context.getLastName();

        if (context.getAge() != null)
            this.age = context.getAge();

        this.pets = context.getPets() == null ? this.pets : new HashSet<>(context.getPets());

        return this;
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

    public int getAge() {
        return age;
    }

    public Set<String> getPets() {
        return pets;
    }
}

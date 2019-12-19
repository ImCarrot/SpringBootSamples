package com.carrot.samples.springbootcrudrepository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCountResponseDto {

    @JsonProperty("NoOfUsers")
    private final long userCount;

    public UserCountResponseDto(long userCount) {
        this.userCount = userCount;
    }

    public long getUserCount() {
        return userCount;
    }
}

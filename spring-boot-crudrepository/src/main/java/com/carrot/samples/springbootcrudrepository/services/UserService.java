package com.carrot.samples.springbootcrudrepository.services;

import com.carrot.samples.springbootcrudrepository.exceptions.ServiceClientException;
import com.carrot.samples.springbootcrudrepository.models.UserCountResponseDto;
import com.carrot.samples.springbootcrudrepository.models.UserDto;

import java.util.Collection;

public interface UserService {

    String createUser(UserDto context) throws ServiceClientException;

    UserCountResponseDto userCount();

    Collection<UserDto> getUsers();

    UserDto getUsers(String userId) throws ServiceClientException;

    boolean updateUser(String userId, UserDto context) throws ServiceClientException;

    boolean deleteUser(String userId) throws ServiceClientException;
}

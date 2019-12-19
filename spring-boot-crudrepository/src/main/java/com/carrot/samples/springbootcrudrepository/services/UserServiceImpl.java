package com.carrot.samples.springbootcrudrepository.services;

import com.carrot.samples.springbootcrudrepository.exceptions.ServiceClientException;
import com.carrot.samples.springbootcrudrepository.models.UserCountResponseDto;
import com.carrot.samples.springbootcrudrepository.models.UserDto;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    @Override
    public String createUser(UserDto context) throws ServiceClientException {
        return null;
    }

    @Override
    public UserCountResponseDto userCount() {
        return null;
    }

    @Override
    public Collection<UserDto> getUsers() {
        return null;
    }

    @Override
    public UserDto getUsers(String userId) throws ServiceClientException {
        return null;
    }

    @Override
    public boolean updateUser(String userId, UserDto context) throws ServiceClientException {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) throws ServiceClientException {
        return false;
    }
}

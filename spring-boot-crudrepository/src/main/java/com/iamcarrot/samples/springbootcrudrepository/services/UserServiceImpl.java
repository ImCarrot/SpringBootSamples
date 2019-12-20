package com.iamcarrot.samples.springbootcrudrepository.services;

import com.iamcarrot.samples.springbootcrudrepository.exceptions.ServiceClientException;
import com.iamcarrot.samples.springbootcrudrepository.models.UserCountResponseDto;
import com.iamcarrot.samples.springbootcrudrepository.models.UserDao;
import com.iamcarrot.samples.springbootcrudrepository.models.UserDto;
import com.iamcarrot.samples.springbootcrudrepository.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(UserDto context) throws ServiceClientException {

        if (context == null)
            throw new ServiceClientException("The user sign up context cannot be null");

        try {

            UserDao savedUser = userRepository.save(new UserDao(context));

            return savedUser.getUserId();

        } catch (DuplicateKeyException e) {
            throw new ServiceClientException("A user with the same username already exists");

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserCountResponseDto userCount() {

        long userCount = userRepository.count();

        return new UserCountResponseDto(userCount);
    }

    @Override
    public UserCountResponseDto userCount(int lessThanAge) {

        long userCount = userRepository.fetchUsersOfLessAge(lessThanAge);

        return new UserCountResponseDto(userCount);
    }

    @Override
    public Collection<UserDto> getUsers() {

        Iterable<UserDao> users = userRepository.findAll();

        if (!users.iterator().hasNext())
            return null;

        Collection<UserDto> returner = new ArrayList<>();
        users.forEach(x -> returner.add(new UserDto(x)));

        return returner;
    }

    @Override
    public UserDto getUsers(String userId) throws ServiceClientException {

        if (userId == null || userId.trim().isEmpty())
            throw new ServiceClientException("The userId cannot be empty");

        Optional<UserDao> userOptional = userRepository.findById(userId);

        UserDao user = userOptional.orElse(null);

        if (user == null)
            throw new ServiceClientException("No user exists with that Id");

        return new UserDto(user);
    }

    @Override
    public Collection<UserDto> getUsersByPet(String petName) {

        Collection<UserDao> users = userRepository.findByPets(petName);

        if (users == null || users.isEmpty())
            return null;

        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public boolean updateUser(String userId, UserDto context) throws ServiceClientException {

        if (userId == null || userId.trim().isEmpty())
            throw new ServiceClientException("The userId cannot be empty");

        if (context == null)
            throw new ServiceClientException("The context cannot be null");

        try {

            Optional<UserDao> userOptional = userRepository.findById(userId);

            UserDao user = userOptional.orElse(null);

            if (user == null)
                throw new ServiceClientException("No user exists with that Id");

            userRepository.save(user.updatedFrom(context));

            return true;

        } catch (DuplicateKeyException e) {
            throw new ServiceClientException("A user with the same username already exists");

        } catch (ServiceClientException e){
            throw e;

        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String userId) throws ServiceClientException {

        if (userId == null || userId.trim().isEmpty())
            throw new ServiceClientException("The userId cannot be empty");

        userRepository.deleteById(userId);

//        UserDao existingUser = userRepository.findById(userId).orElse(null);
//        userRepository.delete(existingUser);


        return false;
    }
}

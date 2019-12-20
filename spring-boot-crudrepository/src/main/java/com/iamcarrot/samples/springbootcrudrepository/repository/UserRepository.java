package com.iamcarrot.samples.springbootcrudrepository.repository;

import com.iamcarrot.samples.springbootcrudrepository.models.UserDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<UserDao, String> {

    @Query(value = "{age: {$lte: ?0}}", count = true)
    long fetchUsersOfLessAge(int age);

    Collection<UserDao> findByPets(String petName);

}

package com.carrot.samples.springbootcrudrepository.repository;

import com.carrot.samples.springbootcrudrepository.models.UserDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, String> {

    @Query("{age: {$lte: ?0}}")
    UserDao fetchUsersOfLessAge(int age);

    UserDao findByPetName(String petName);

}

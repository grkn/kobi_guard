package com.kobiguard.app.repository;

import com.kobiguard.app.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}

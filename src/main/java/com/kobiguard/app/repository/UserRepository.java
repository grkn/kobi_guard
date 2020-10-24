package com.kobiguard.app.repository;

import com.kobiguard.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User, String>, PagingAndSortingRepository<User, String> {

}

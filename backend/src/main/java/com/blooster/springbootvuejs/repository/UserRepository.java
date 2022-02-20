package com.blooster.springbootvuejs.repository;

import com.blooster.springbootvuejs.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}

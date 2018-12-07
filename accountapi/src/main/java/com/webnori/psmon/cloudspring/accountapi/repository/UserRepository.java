package com.webnori.psmon.cloudspring.accountapi.repository;

import com.webnori.psmon.cloudspring.accountapi.persistence.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}

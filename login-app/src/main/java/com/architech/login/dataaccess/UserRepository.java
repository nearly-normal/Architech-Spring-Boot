package com.architech.login.dataaccess;

import com.architech.login.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Spring Managed Repository object for persistence interfaces.
 *
 * Spring injects a proxy implementation for Crud methods and finder methods.
 *
 * Created by satish on 15-01-14.
 */

public interface UserRepository extends CrudRepository<User,Long>{

    public List<User> findByUserName(String userName);

}

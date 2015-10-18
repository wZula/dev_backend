package com.tourgoat.users.repositories;

import com.tourgoat.users.models.TestUser;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Transactional
@Repository
public interface TestUserDao extends CrudRepository<TestUser, Long> {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email the user email.
     */
    public TestUser findByEmail(String email);

} // class UserDao

package com.tourgoat;

import com.tourgoat.users.models.TestUser;
import com.tourgoat.users.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tourgoat.users.repositories.TestUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {VelocityAutoConfiguration.class})
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TestUserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //user damy data
//        userDao.save(new User("N", null, null, null, null, null, null, null, null, null));
        // save a couple of users
        userDao.save(new TestUser("fitsum@email.com", "Fitsum"));
        userDao.save(new TestUser("weni@email.com", "Weni"));
        userDao.save(new TestUser("seifu@email.com", "Seifu"));
        userDao.save(new TestUser("dave@gmail.com", "Dave"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (TestUser user : userDao.findAll()) {
            log.info(user.toString());
        }
        System.out.println();

        // fetch an individual user by ID
        TestUser user = userDao.findOne(1L);
        log.info("Customer found with findOne(1L):");
        log.info("--------------------------------");
        log.info(user.toString());

    
    }

}

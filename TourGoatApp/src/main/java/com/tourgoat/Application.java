package com.tourgoat;

import com.tourgoat.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tourgoat.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // save a couple of users
        userDao.save(new User("Fitsum", "fitsum@email.com"));
        userDao.save(new User("Weni", "weni@email.com"));
        userDao.save(new User("Seifu", "seifu@email.com"));
        userDao.save(new User("David", "dave@gmail.com"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (User user : userDao.findAll()) {
            log.info(user.toString());
        }
        System.out.println();

        // fetch an individual user by ID
        User user = userDao.findOne(1L);
        log.info("Customer found with findOne(1L):");
        log.info("--------------------------------");
        log.info(user.toString());

    
    }

}

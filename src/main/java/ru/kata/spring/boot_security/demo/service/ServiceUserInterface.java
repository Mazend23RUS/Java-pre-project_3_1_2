package ru.kata.spring.boot_security.demo.service;




import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface ServiceUserInterface {

    List<User> getAllUsersList();

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);

//    Optional<User> getUserByName(String userName);
    User getUserByName(String userName);

}

package ru.kata.spring.boot_security.demo.DAO;




import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserInterface {

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);
}

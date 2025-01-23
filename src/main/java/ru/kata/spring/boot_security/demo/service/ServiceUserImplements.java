package ru.kata.spring.boot_security.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserInterface;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
@Transactional
public class ServiceUserImplements implements ServiceUserInterface {
   @Autowired
   private UserInterface userInterface;

    @Override
    public void addUser(User user){
        userInterface.saveUser(user);
    }

    @Override
    public List<User> getAllUsersList() {
        return userInterface.getAllUsers();
    }
    @Override
    public void deleteUser(Long id) { userInterface.deleteUser(id);}

    @Override
    public void updateUser(User user){
        userInterface.updateUser(user);
    }

    @Override
    public User getUserById(Long id){

        return userInterface.getUserById(id);
    }
}

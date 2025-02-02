package ru.kata.spring.boot_security.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserInterface;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceUserImplements implements ServiceUserInterface {
   private final UserInterface userInterface;

    public ServiceUserImplements(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

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
    public void updateUser(User user){userInterface.updateUser(user);}

    @Override
    public User getUserById(Long id){ return userInterface.getUserById(id);}

//    @Override
//    public Optional<User> getUserByName(String userName) {
//        return userInterface.getUserByName(userName);
//    }

    @Override
    public User getUserByName(String userName) {
        return userInterface.getUserByName(userName);
    }



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = getUserByName(username);
//        user.map(User::new)
//                .orElseThrow(() -> new UsernameNotFoundException( username + " This user not found"));
//
//
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(String.valueOf(user.map((Function<? super User, ?>) User::getUsername)))
//                .password(String.valueOf(user.map((Function<? super User, ?>) User::getPassword)))
//                .roles(String.valueOf(user.stream().map(m -> m.getRole().stream().map(Role::getNameRole))))
//                .build();
//    }


}

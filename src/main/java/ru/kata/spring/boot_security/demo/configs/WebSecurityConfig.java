package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.kata.spring.boot_security.demo.DAO.UserInterface;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.service.ServiceUserImplements;
import ru.kata.spring.boot_security.demo.service.ServiceUserInterface;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements UserDetailsService {
    private final SuccessUserHandler successUserHandler;
    private final ServiceUserImplements serviceUserImplements;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, ServiceUserImplements serviceUserImplements) {
        this.successUserHandler = successUserHandler;
        this.serviceUserImplements = serviceUserImplements;
    }

    private User user;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(WebSecurityConfig.this);
//                .passwordEncoder(passwordEncoder());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       Optional<ru.kata.spring.boot_security.demo.models.User> userForDetailService = serviceUserImplements.getUserByName(username);
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        ru.kata.spring.boot_security.demo.models.User userForDetailService = serviceUserImplements.getUserByName(username);

        if (!userForDetailService.isEnabled()) {
            throw new UsernameNotFoundException(userForDetailService + " User not found");
        }

        return User.builder()
                .username(userForDetailService.getUsername())
                .password(passwordEncoder.encode(userForDetailService.getPassword()))
//                .roles(Arrays.toString(userForDetailService.getAuthorities().toArray()))
                .roles(String.valueOf(userForDetailService.getRole().stream().map(Role::getNameRole).collect(Collectors.toList())))
                .build();
    }
    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

}
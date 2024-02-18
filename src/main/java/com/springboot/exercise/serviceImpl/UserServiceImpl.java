package com.springboot.exercise.serviceImpl;

import com.springboot.exercise.config.SecurityConfig;
import com.springboot.exercise.models.Role;
import com.springboot.exercise.models.Student;
import com.springboot.exercise.models.User;
import com.springboot.exercise.repositories.StudentRepository;
import com.springboot.exercise.repositories.UserRespository;
import com.springboot.exercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRespository userRespository;
    @Override
    public User create(User user) {
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userRespository.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRespository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside usr impl");
        System.out.println(username);
        User user = userRespository.findByEmail(username);
        if (user != null) {

            var result = getGrantedAuthorities(user);
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getGrantedAuthorities(user)
            );
        } else {
            System.out.println("usr not found");
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        Role role = Optional.ofNullable(user.getRole()).orElse(new Role());
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    private Collection<? extends GrantedAuthority>mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}

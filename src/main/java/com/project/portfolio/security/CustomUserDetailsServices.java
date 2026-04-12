package com.project.portfolio.security;

import com.project.portfolio.model.Users;
import com.project.portfolio.repo.UsersRepo;
import com.project.portfolio.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServices implements UserDetailsService {

    private final UsersServiceImpl usersServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersServiceImpl.findByUserNames(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not Found::"+username);
        }
        return User.builder()
                .username(users.getUserName())
                .password(users.getPassword())
                .roles(users.getRole().name())
                .build();
    }
}

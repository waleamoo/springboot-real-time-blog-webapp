package com.techqwerty.sprigboot.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.techqwerty.sprigboot.entity.User;
import com.techqwerty.sprigboot.repository.UserRepository;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            org.springframework.security.core.userdetails.User authenticatedUser =
                    new org.springframework.security.core.userdetails.User(
                      user.getEmail(),
                      user.getPassword(),
                      user.getRoles().stream()
                              .map((role) -> new SimpleGrantedAuthority(role.getName()))
                              .collect(Collectors.toList())
                    );
            return authenticatedUser;
        }else{
            throw new UsernameNotFoundException("Invalid username and password");
        }
    }
    
}

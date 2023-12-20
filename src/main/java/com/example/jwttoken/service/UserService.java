package com.example.jwttoken.service;

import com.example.jwttoken.dto.CreateUserRequest;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.UserRepository;
import com.example.jwttoken.response.CreateUserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public CreateUserResponse createUser(CreateUserRequest request){
        if(!userRepository.findByUsername(request.getUsername()).isEmpty()){
            return CreateUserResponse.builder().message("This username already used").build();
        }
        User newUser = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .authorities(request.getAuthorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        var registeredUser = userRepository.save(newUser);
        var token = jwtService.generateToken(registeredUser.getUsername());
        registeredUser.setToken(token);
        return CreateUserResponse.builder()
                .user(registeredUser)
                .message("Success").build();
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }
}

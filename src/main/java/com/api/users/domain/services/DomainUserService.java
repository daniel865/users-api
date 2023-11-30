package com.api.users.domain.services;

import com.api.users.application.UserMapper;
import com.api.users.domain.entities.User;
import com.api.users.application.exceptions.EmailAlreadyRegisteredException;
import com.api.users.domain.valueobjects.Token;
import com.api.users.infrastructure.UserRepository;
import com.api.users.infrastructure.records.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
public class DomainUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtService jwtService;

    public Mono<User> createUser(User user) {
        return userRepository.findByEmail(user.getEmail().value())
            .map(Optional::of)
            .defaultIfEmpty(Optional.empty())
            .flatMap(optionalUser -> {
                if (!optionalUser.isEmpty()) {
                    return Mono.error(new EmailAlreadyRegisteredException());
                }

                UserRecord userRecordToSave = UserRecord.from(user);
                return userRepository.save(userRecordToSave)
                        .map(userRecord -> {
                            String token = jwtService.generateToken(userRecord.getEmail());
                            User createdUser = UserMapper.mapTo(userRecord, user, new Token(token));
                            phoneService.addPhones(createdUser);
                            tokenService.addToken(createdUser);
                            return createdUser;
                        });
            });
    }
}

package com.api.users.domain.services;

import com.api.users.domain.entities.Phone;
import com.api.users.domain.entities.User;
import reactor.core.publisher.Flux;

public interface PhoneService {
    Flux<Phone> addPhones(User user);
}

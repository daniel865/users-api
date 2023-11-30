package com.api.users.infrastructure;

import com.api.users.infrastructure.records.UserRecord;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<UserRecord, UUID> {
    @Query("Select * from User where email = $1")
    Mono<UserRecord> findByEmail(String email);
}

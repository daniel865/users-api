package com.api.users.infrastructure;

import com.api.users.infrastructure.records.UserRecord;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserRepository extends R2dbcRepository<UserRecord, UUID> {
    Mono<UserRecord> findByEmail(String email);
}

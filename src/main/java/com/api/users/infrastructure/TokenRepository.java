package com.api.users.infrastructure;

import com.api.users.infrastructure.records.TokenRecord;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TokenRepository extends ReactiveCrudRepository<TokenRecord, UUID> {
    @Query("Select * from tokens where user_id = $1")
    Mono<TokenRecord> findByUserId(UUID user_id);
}

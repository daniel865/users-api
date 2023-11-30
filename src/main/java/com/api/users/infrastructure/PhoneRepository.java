package com.api.users.infrastructure;

import com.api.users.infrastructure.records.PhoneRecord;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface PhoneRepository extends ReactiveCrudRepository<PhoneRecord, UUID> {
    @Query("Select * from phone where user_id = $1")
    Flux<PhoneRecord> findByUserId(UUID user_id);
}

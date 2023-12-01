package com.api.users.infrastructure;

import com.api.users.infrastructure.records.PhoneRecord;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface PhoneRepository extends R2dbcRepository<PhoneRecord, UUID> {
    @Query("Select * from phone where user_id = $1")
    Flux<PhoneRecord> findByUserId(UUID user_id);
}

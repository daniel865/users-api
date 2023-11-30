package com.api.users.infrastructure.records;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Phone")
public class PhoneRecord {
    @Id
    private UUID id;
    private UUID user_id;
    private String number;
    private String cityCode;
    private String countryCode;
}

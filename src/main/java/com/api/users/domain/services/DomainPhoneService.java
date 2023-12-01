package com.api.users.domain.services;

import com.api.users.domain.entities.Phone;
import com.api.users.domain.entities.User;
import com.api.users.domain.valueobjects.CityCode;
import com.api.users.domain.valueobjects.CountryCode;
import com.api.users.domain.valueobjects.PhoneNumber;
import com.api.users.infrastructure.PhoneRepository;
import com.api.users.infrastructure.records.PhoneRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DomainPhoneService implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public Flux<Phone> addPhones(User user) {
        List<PhoneRecord> phoneRecords = user
                .getPhones()
                .stream()
                .map(phone ->
                        new PhoneRecord(
                                UUID.randomUUID(),
                                UUID.fromString(user.getUserId().value()),
                                phone.getPhoneNumber().value(),
                                phone.getCityCode().value(),
                                phone.getCountryCode().value()
                        )
                )
                .collect(Collectors.toList());
        return phoneRepository.saveAll(phoneRecords)
                .map(phoneRecord ->
                        new Phone(
                                new PhoneNumber(phoneRecord.getNumber()),
                                new CityCode(phoneRecord.getCityCode()),
                                new CountryCode(phoneRecord.getCountryCode())
                        )
                );
    }
}

package com.api.users.domain.entities;

import com.api.users.domain.valueobjects.CityCode;
import com.api.users.domain.valueobjects.CountryCode;
import com.api.users.domain.valueobjects.PhoneNumber;

public class Phone {
    private PhoneNumber number;
    private CityCode cityCode;
    private CountryCode countryCode;

    public Phone(PhoneNumber number, CityCode cityCode, CountryCode countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public PhoneNumber getPhoneNumber() {
        return number;
    }

    public CityCode getCityCode() {
        return cityCode;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }
}

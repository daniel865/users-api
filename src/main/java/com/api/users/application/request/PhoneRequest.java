package com.api.users.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    private String number;
    private String cityCode;
    private String countryCode;
}

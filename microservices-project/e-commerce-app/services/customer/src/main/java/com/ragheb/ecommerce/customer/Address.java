package com.ragheb.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}

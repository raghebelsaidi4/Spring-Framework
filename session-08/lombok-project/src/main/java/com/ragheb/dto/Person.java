package com.ragheb.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class Person {

    private Integer id;
    private String name;
    private String gender;
    private String phone;
    private Date birthday;
}

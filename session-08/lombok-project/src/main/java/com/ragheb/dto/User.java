package com.ragheb.dto;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
}

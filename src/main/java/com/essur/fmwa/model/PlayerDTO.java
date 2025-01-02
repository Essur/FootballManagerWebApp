package com.essur.fmwa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer experience;
    private Integer age;
}

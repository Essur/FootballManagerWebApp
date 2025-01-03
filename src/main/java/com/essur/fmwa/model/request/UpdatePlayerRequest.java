package com.essur.fmwa.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlayerRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer experience;
    private Integer age;
    private Long teamId;
}

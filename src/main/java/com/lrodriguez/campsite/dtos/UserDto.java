package com.lrodriguez.campsite.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UserDto {

    @NotBlank(message = "Field email is mandatory")
    @Email(message = "Field email is invalid")
    private String email;

    @NotBlank(message = "Field full_name is mandatory")
    private String fullname;

}

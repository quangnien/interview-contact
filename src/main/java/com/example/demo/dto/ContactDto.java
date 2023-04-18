package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private Long id;

    @NotBlank(message = "First name is mandatory!")
    private String firstName;

    @NotBlank(message = "Last name is mandatory!")
    private String lastName;

    @Email(message = "Email address is not valid!")
    @NotBlank(message = "Last name is mandatory!")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})" , message = "Phone number must be 10 digits!")
    private String phoneNumber;

    @Length(min = 15 , message = "The postal address should have more than 15 characters!")
    private String postalAddress;
}

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank(message = "First name is mandatory!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is mandatory!")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email address is not valid!")
    @NotBlank(message = "Last name is mandatory!")
	@Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits!")
    private String phoneNumber;

    @Column(name = "postal_address")
    @Length(min = 15 , message = "The postal address should have more than 15 characters!")
    private String postalAddress;
}

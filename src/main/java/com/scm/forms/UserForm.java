package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6,message = "Password must Atleast of 6 characters")
    private String password;
    @Size(max=100)
    @NotBlank(message = "About is required")
    private String about;
    @Size(max=12,min=10,message = "Invalid Phone Number")
    private String phoneNumber;

    
}

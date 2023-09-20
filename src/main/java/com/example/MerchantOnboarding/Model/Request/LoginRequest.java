package com.example.MerchantOnboarding.Model.Request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message="E-Mail cannot be empty!")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+=?`{|}~^.-]+@[a-zA-Z0-9.]+(\\.)+[a-zA-Z]{3}$",message = "Add single character('@') only!")
    private String email;

    @NotEmpty(message="Password cannot be empty!")
    private String passcode;
}

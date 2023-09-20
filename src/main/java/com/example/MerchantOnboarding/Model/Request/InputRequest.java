package com.example.MerchantOnboarding.Model.Request;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InputRequest {

    @NotEmpty(message="Business Name cannot be empty!")
    private String businessName;

    @Email(message="Invalid format for E-Mail!")
//    @NotEmpty(message="E-Mail cannot be empty!")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+=?`{|}~^.-]+@[a-zA-Z0-9.]+(\\.)+[a-zA-Z]{3}$",message = "Add single character('@') only!")
    private String email;
    @NotEmpty(message="Phone Number cannot be null!")
    @Size(min=12, message = "Type at least 13 digits!")
    @Size(max=15, message = "Type at most 15 digits!")
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$",message = "Add your country code!")
    private String phoneNumber;
}

package com.example.MerchantOnboarding.Model.Response;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class InputResponse {
    private String email;
    private String passcode;
    private String message;
}

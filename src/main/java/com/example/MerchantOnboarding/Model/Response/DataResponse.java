package com.example.MerchantOnboarding.Model.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataResponse {
    private int id;
    private String businessName;
    private String email;
    private String phoneNumber;
    private String Type;
    private String status;
}

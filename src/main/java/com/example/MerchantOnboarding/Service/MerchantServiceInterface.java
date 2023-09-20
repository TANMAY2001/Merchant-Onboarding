package com.example.MerchantOnboarding.Service;
import com.example.MerchantOnboarding.Model.Request.LoginRequest;
import com.example.MerchantOnboarding.Model.Request.InputRequest;
import com.example.MerchantOnboarding.Model.Response.*;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface MerchantServiceInterface {
    ResponseEntity<List<DataResponse>> login(LoginRequest loginRequest);
    ResponseEntity<InputResponse> addMerchant(InputRequest inputRequest);
    ResponseEntity<List<DataResponse>> showMerchants(String businessName);
    ResponseEntity<MessageResponse> deleteMerchant(int id);
    ResponseEntity<MessageResponse> inActivation(int id);
}

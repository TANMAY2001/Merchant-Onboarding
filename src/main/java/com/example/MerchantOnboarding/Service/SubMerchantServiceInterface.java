package com.example.MerchantOnboarding.Service;
import com.example.MerchantOnboarding.Model.Request.InputRequest;
import com.example.MerchantOnboarding.Model.Response.DataResponse;
import com.example.MerchantOnboarding.Model.Response.MessageResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface SubMerchantServiceInterface {
    ResponseEntity<MessageResponse> addSubMerchant( InputRequest inputRequest);
    ResponseEntity<List<DataResponse>>  showSubMerchants(String businessName);
    ResponseEntity<MessageResponse> deleteSubMerchant( int id);
    ResponseEntity<MessageResponse> inActivation( int id);
}

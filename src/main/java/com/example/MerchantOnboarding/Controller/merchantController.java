package com.example.MerchantOnboarding.Controller;
import com.example.MerchantOnboarding.Model.Request.InputRequest;
import com.example.MerchantOnboarding.Model.Response.*;
import com.example.MerchantOnboarding.Service.Implementation.SubMerchantServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class merchantController {
    @Autowired
    private  SubMerchantServiceImplementation subMerchantServiceImplementation;

    @PostMapping("/login/merchant/add")
    public ResponseEntity<MessageResponse> addSubMerchant(@Valid @RequestBody InputRequest inputRequest){
        return subMerchantServiceImplementation.addSubMerchant(inputRequest);
    }

    @GetMapping("/login/merchant/show")
    public ResponseEntity<List<DataResponse>>  showSubMerchants(@Valid @RequestParam(value = "business_name", required = true) String businessName){
        return subMerchantServiceImplementation.showSubMerchants(businessName);
    }

    @DeleteMapping("/login/merchant/delete")
    public ResponseEntity<MessageResponse> deleteSubMerchant(@Valid @RequestParam(value="id", required = true) int id){
          return subMerchantServiceImplementation.deleteSubMerchant(id);
    }

    @PutMapping ("/login/merchant/status")
    public ResponseEntity<MessageResponse> inActivation(@Valid @RequestParam(value="id", required = true) int id){
            return subMerchantServiceImplementation.inActivation(id);
    }

}
package com.example.MerchantOnboarding.Controller;
import com.example.MerchantOnboarding.Model.Request.LoginRequest;
import com.example.MerchantOnboarding.Model.Request.InputRequest;
import com.example.MerchantOnboarding.Model.Response.*;
import com.example.MerchantOnboarding.Service.Implementation.MerchantServiceImplementation;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Data
@RestController
public class adminController {
    @Autowired
private  MerchantServiceImplementation merchantServiceImplementation;

    @PostMapping("/login")
    public ResponseEntity<List<DataResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        return merchantServiceImplementation.login(loginRequest);
    }

    @PostMapping("/login/admin/add")
    public ResponseEntity<InputResponse> addMerchant(@Valid @RequestBody InputRequest inputRequest){
        return merchantServiceImplementation.addMerchant(inputRequest);
}

    @GetMapping("/login/admin/show")
    public ResponseEntity<List<DataResponse>> showMerchants(@Valid @RequestParam(value = "business_name",required = false) String businessName){
        return merchantServiceImplementation.showMerchants(businessName);
    }

@DeleteMapping("/login/admin/delete")
public ResponseEntity<MessageResponse> deleteMerchant(@Valid @RequestParam(value="id", required = true) int id){
        return merchantServiceImplementation.deleteMerchant(id);
}

@PutMapping ("/login/admin/status")
public ResponseEntity<MessageResponse> inActivation(@Valid @RequestParam(value="id", required = true) int id){
    return merchantServiceImplementation.inActivation(id);
}

}

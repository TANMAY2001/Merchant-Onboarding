package com.example.MerchantOnboarding.Service.Implementation;
import com.example.MerchantOnboarding.Data.Entity.SubMerchant;
import com.example.MerchantOnboarding.Exceptions.*;
import com.example.MerchantOnboarding.Model.Request.InputRequest;
import com.example.MerchantOnboarding.Model.Response.*;
import com.example.MerchantOnboarding.Data.Repository.SubMerchantRepository;
import com.example.MerchantOnboarding.Service.SubMerchantServiceInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class SubMerchantServiceImplementation implements SubMerchantServiceInterface {

    @Autowired
       SubMerchantRepository subMerchantRepository;

    @Override
    public ResponseEntity<MessageResponse> addSubMerchant(InputRequest inputRequest) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            SubMerchant subMerchant = new SubMerchant();
            SubMerchant subMerchant1 = subMerchantRepository.checkPhoneNumber(inputRequest.getPhoneNumber());
           if(subMerchant1 !=null){
               throw new CustomException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
           }
           else{
            subMerchant.setBusinessName(inputRequest.getBusinessName());
            subMerchant.setEmail(inputRequest.getEmail());
            subMerchant.setPhoneNumber(inputRequest.getPhoneNumber());
            subMerchant.setType("seller");
            subMerchant.setCreatedAt(new Date());
            subMerchant.setModifiedAt(new Date());
            subMerchant.setDeleted(false);
            subMerchant.setRole("sub-merchant");
            subMerchant.setStatus("Active");
            subMerchantRepository.save(subMerchant);

            messageResponse.setMessage("Sub-Merchant details saved!");
           }
        }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during adding sub-merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DataResponse>>  showSubMerchants(String businessName) {
        List <DataResponse> subMerchantList = new ArrayList<>();
        try{
        List <SubMerchant> subMerchant = subMerchantRepository.checkDeleted();
            List <SubMerchant> subMerchant1 = subMerchantRepository.checkBusinessName(businessName);
            if(subMerchant != null){
              if(subMerchant1 !=null){
                  for(SubMerchant subMerchant2:subMerchant1){
                      DataResponse dataResponse = new DataResponse();
                      dataResponse.setId(subMerchant2.getId());
                      dataResponse.setBusinessName(subMerchant2.getBusinessName());
                      dataResponse.setEmail(subMerchant2.getEmail());
                      dataResponse.setPhoneNumber(subMerchant2.getPhoneNumber());
                      dataResponse.setType(subMerchant2.getType());
                      dataResponse.setStatus(subMerchant2.getStatus());
                      subMerchantList.add(dataResponse);
                  }
              }
              else{
                  for(SubMerchant subMerchant3:subMerchant){
                      DataResponse dataResponse = new DataResponse();
                      dataResponse.setId(subMerchant3.getId());
                      dataResponse.setBusinessName(subMerchant3.getBusinessName());
                      dataResponse.setEmail(subMerchant3.getEmail());
                      dataResponse.setPhoneNumber(subMerchant3.getPhoneNumber());
                      dataResponse.setType(subMerchant3.getType());
                      dataResponse.setStatus(subMerchant3.getStatus());
                      subMerchantList.add(dataResponse);
                  }
              }
            }
            else {
                throw new CustomException(ErrorCode.NOT_FOUND);
            }
        }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during showing sub-merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(subMerchantList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteSubMerchant(int id){
        MessageResponse messageResponse = new MessageResponse();
        try{
        SubMerchant subMerchant = subMerchantRepository.checkId(id);
        if(subMerchant.isDeleted()){
            throw new CustomException(ErrorCode.ALREADY_REPORTED);
        }
        else{
            subMerchant.setDeleted(true);
            messageResponse.setMessage("Deleted Successfully");
        }
        subMerchantRepository.save(subMerchant);
        }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during deleting sub-merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> inActivation(int id){
        MessageResponse messageResponse = new MessageResponse();
        try{
        SubMerchant subMerchant = subMerchantRepository.checkId(id);
        if(subMerchant.getStatus().equalsIgnoreCase("active") ){
            subMerchant.setStatus("Inactive");
            throw new CustomException(ErrorCode.STATUS_INACTIVE);
        }
        else{
            subMerchant.setStatus("Active");
            messageResponse.setMessage("Status updated to Active Successfully");
        }
        subMerchantRepository.save(subMerchant);
        }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during during changing status of sub-merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
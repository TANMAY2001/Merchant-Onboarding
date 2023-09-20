package com.example.MerchantOnboarding.Service.Implementation;
import com.example.MerchantOnboarding.Data.Entity.Merchant;
import com.example.MerchantOnboarding.Data.Entity.SubMerchant;
import com.example.MerchantOnboarding.Data.Repository.SubMerchantRepository;
import com.example.MerchantOnboarding.Exceptions.ErrorCode;
import com.example.MerchantOnboarding.Exceptions.CustomException;
import com.example.MerchantOnboarding.Model.Request.LoginRequest;
import com.example.MerchantOnboarding.Model.Request.InputRequest;
import com.example.MerchantOnboarding.Model.Response.*;
import com.example.MerchantOnboarding.Data.Repository.MerchantRepository;
import com.example.MerchantOnboarding.Service.MerchantServiceInterface;
import com.example.MerchantOnboarding.Utils.Utils;
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
public class MerchantServiceImplementation implements MerchantServiceInterface {
    @Autowired
     MerchantRepository merchantRepository;

    @Autowired
     SubMerchantRepository subMerchantRepository;

    @Override
    public ResponseEntity<List<DataResponse>> login(LoginRequest loginRequest) {
        List<DataResponse> merchantList = new ArrayList<>();
        try {
            MessageResponse messageResponse = new MessageResponse();
            Merchant merchant = merchantRepository.checkEmail(loginRequest.getEmail());
            if (merchant == null){
                throw new CustomException(ErrorCode.EMAIL_INVALID);
            }
           else {
                if (merchant.getRole().equalsIgnoreCase("admin")) {
                    String S1 = Utils.Encryption(loginRequest.getPasscode());
                    if (S1.equalsIgnoreCase(merchant.getPasscode())) {
                        List<Merchant> merchant1 = merchantRepository.checkDeleted();
                        for (Merchant merchant2 : merchant1) {
                            DataResponse dataResponse = new DataResponse();
                            messageResponse.setMessage("Admin logged in!");
                            dataResponse.setId(merchant2.getId());
                            dataResponse.setBusinessName(merchant2.getBusinessName());
                            dataResponse.setEmail(merchant2.getEmail());
                            dataResponse.setPhoneNumber(merchant2.getPhoneNumber());
                            dataResponse.setType(merchant2.getType());
                            dataResponse.setStatus(merchant2.getStatus());
                            merchantList.add(dataResponse);
                        }
                    } else {
                        throw new CustomException(ErrorCode.PASSWORD_INVALID);
                    }
                } else if (merchant.getRole().equalsIgnoreCase("merchant")) {
                    String S2 = Utils.Encryption(loginRequest.getPasscode());
                    if (S2.equalsIgnoreCase(merchant.getPasscode())) {
                        SubMerchant subMerchant = new SubMerchant();
                        List<SubMerchant> subMerchant1 = subMerchantRepository.checkBusinessName(merchant.getBusinessName());
                        if(subMerchant1==null){
                            throw new CustomException(ErrorCode.BAD_REQUEST);
                        }
                        else{
                            for (SubMerchant subMerchant2 : subMerchant1) {
                                DataResponse dataResponse = new DataResponse();
                                messageResponse.setMessage("Merchant logged in!");
                                dataResponse.setId(subMerchant2.getId());
                                dataResponse.setBusinessName(subMerchant2.getBusinessName());
                                dataResponse.setEmail(subMerchant2.getEmail());
                                dataResponse.setPhoneNumber(subMerchant2.getPhoneNumber());
                                dataResponse.setType(subMerchant2.getType());
                                dataResponse.setStatus(subMerchant2.getStatus());
                                merchantList.add(dataResponse);
                            }
                        }
                    } else {
                        throw new CustomException(ErrorCode.PASSWORD_INVALID);
                    }
                } else {
                    messageResponse.setMessage("Only Admin or Merchant can log in!");
                    throw new CustomException(ErrorCode.BAD_REQUEST);
                }
            }
        }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during login : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(merchantList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InputResponse> addMerchant(InputRequest inputRequest) {
        InputResponse inputResponse = new InputResponse();
        try {
            String password = Utils.PasswordGenerator();
            String Id = Utils.UUIDGenerator();
            Merchant merchant = new Merchant();
            Merchant merchant1 = merchantRepository.checkBusinessName(inputRequest.getBusinessName());
            Merchant merchant2 = merchantRepository.checkPhoneNumber(inputRequest.getPhoneNumber());
            Merchant merchant3 = merchantRepository.checkEmail(inputRequest.getEmail());
            if (merchant1 != null) {
                throw new CustomException(ErrorCode.BUSINESS_NAME_ALREADY_EXISTS);
            } if (merchant2 != null) {
                throw new CustomException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
            }
            if(merchant3 != null){
                throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
            }
            else {
                merchant.setBusinessName(inputRequest.getBusinessName());
                merchant.setEmail(inputRequest.getEmail());
                merchant.setPhoneNumber(inputRequest.getPhoneNumber());
                merchant.setType("wholeSeller");
                merchant.setUniqueId(Id);
                merchant.setCreatedAt(new Date());
                merchant.setModifiedAt(new Date());
                merchant.setPasscode(Utils.Encryption(password));
                merchant.setDeleted(false);
                merchant.setRole("merchant");
                merchant.setStatus("Active");
                merchantRepository.save(merchant);

                inputResponse.setMessage("Merchant details saved!");
                inputResponse.setEmail(merchant.getEmail());
                inputResponse.setPasscode(password);
            }
        }  catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during adding merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(inputResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DataResponse>> showMerchants(String businessName) {
        List<DataResponse> merchantList = new ArrayList<>();
        try {
                if (businessName!=null) {
                    Merchant merchant1 = merchantRepository.checkBusinessName(businessName);
                    DataResponse dataResponse = new DataResponse();
                    dataResponse.setId(merchant1.getId());
                    dataResponse.setBusinessName(merchant1.getBusinessName());
                    dataResponse.setEmail(merchant1.getEmail());
                    dataResponse.setPhoneNumber(merchant1.getPhoneNumber());
                    dataResponse.setType(merchant1.getType());
                    dataResponse.setStatus(merchant1.getStatus());
                    merchantList.add(dataResponse);
                } else {
                    List<Merchant> merchant = merchantRepository.checkDeleted();
                    for (Merchant merchant2 : merchant) {
                        DataResponse dataResponse = new DataResponse();
                        dataResponse.setId(merchant2.getId());
                        dataResponse.setBusinessName(merchant2.getBusinessName());
                        dataResponse.setEmail(merchant2.getEmail());
                        dataResponse.setPhoneNumber(merchant2.getPhoneNumber());
                        dataResponse.setType(merchant2.getType());
                        dataResponse.setStatus(merchant2.getStatus());
                        merchantList.add(dataResponse);
                    }
                }
        }  catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during showing merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return new ResponseEntity<>(merchantList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteMerchant(int id) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Merchant merchant = merchantRepository.checkId(id);
          if(merchant.isDeleted()){
              throw new CustomException(ErrorCode.ALREADY_REPORTED);
          }
          else{
              List<SubMerchant> subMerchant=subMerchantRepository.checkBusinessName(merchant.getBusinessName());
              if(subMerchant==null){
                  merchant.setDeleted(true);
                  messageResponse.setMessage("Deleted Successfully");
              }
              else{
                  for(SubMerchant subMerchant1:subMerchant){
                      subMerchant1.setDeleted(true);
                      subMerchantRepository.save(subMerchant1);
                  }
                  merchant.setDeleted(true);
                  messageResponse.setMessage("Deleted Successfully");
              }
              merchantRepository.save(merchant);
          }
            }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during deleting merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> inActivation(int id) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Merchant merchant = merchantRepository.checkId(id);
            if (merchant != null) {
                if (merchant.getStatus().equalsIgnoreCase("active")) {
                    merchant.setStatus("Inactive");
                    List<SubMerchant> subMerchant = subMerchantRepository.checkBusinessName(merchant.getBusinessName());
                       if(!subMerchant.isEmpty()){
                        for (SubMerchant subMerchant1 : subMerchant) {
                            subMerchant1.setStatus("Inactive");
                            subMerchantRepository.save(subMerchant1);
                        }
                       }
            throw new CustomException(ErrorCode.STATUS_INACTIVE);
                }
                else {
                    merchant.setStatus("Active");
                    List<SubMerchant> subMerchant = subMerchantRepository.checkBusinessName(merchant.getBusinessName());
                    if(!subMerchant.isEmpty()){
                        for (SubMerchant subMerchant1 : subMerchant) {
                            subMerchant1.setStatus("Active");
                            subMerchantRepository.save(subMerchant1);
                        }
                    }
                        messageResponse.setMessage("Status updated to Active Successfully");
                }
                merchantRepository.save(merchant);
            }
            else {
                throw new CustomException(ErrorCode.NOT_FOUND);
            }
        }
        catch(CustomException cex){
            throw cex;
        }
        catch (Exception cex){
            log.error("Error occurred during changing status of merchants : [{}]", cex.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }



}
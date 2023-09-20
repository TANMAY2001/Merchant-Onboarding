package com.example.MerchantOnboarding.Data.Repository;
import com.example.MerchantOnboarding.Data.Entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface MerchantRepository extends JpaRepository<Merchant,Integer> {
    @Query(value="Select * from merchant_data where business_name=:business_name and is_deleted = false",nativeQuery = true)
    Merchant checkBusinessName(String business_name);
    @Query(value="Select * from merchant_data where phone_number=:phone_number",nativeQuery = true)
    Merchant checkPhoneNumber( String phone_number);

    @Query(value = "Select * from merchant_data where is_deleted = false",nativeQuery = true)
    List<Merchant> checkDeleted();

    @Query(value = "Select * from merchant_data where email=:email",nativeQuery = true)
    Merchant checkEmail( String email);

    @Query(value = "Select * from merchant_data where id=:id",nativeQuery = true)
    Merchant checkId( int id);
}

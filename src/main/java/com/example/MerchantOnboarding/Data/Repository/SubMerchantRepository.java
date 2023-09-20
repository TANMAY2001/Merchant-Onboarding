package com.example.MerchantOnboarding.Data.Repository;
import com.example.MerchantOnboarding.Data.Entity.SubMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface SubMerchantRepository extends JpaRepository<SubMerchant,Integer> {
    @Query(value="Select * from sub_merchant_data where business_name=:business_name and is_deleted = false",nativeQuery = true)
    List<SubMerchant> checkBusinessName( String business_name);

    @Query(value="Select * from sub_merchant_data where phone_number=:phone_number",nativeQuery = true)
    SubMerchant checkPhoneNumber( String phone_number);

    @Query(value = "Select * from sub_merchant_data where is_deleted = false",nativeQuery = true)
    List<SubMerchant> checkDeleted();

    @Query(value = "Select * from sub_merchant_data where id=:id",nativeQuery = true)
    SubMerchant checkId( int id);
}

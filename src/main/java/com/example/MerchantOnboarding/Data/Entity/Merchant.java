package com.example.MerchantOnboarding.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="merchant_data")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="business_name")
    private String businessName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="type")
    private String Type;

    @Column(name="unique_id")
    private String uniqueId;

    @Column(name="pass_word")
    private String passcode;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @Column(name="status")
    private String status;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="role")
    private String role;

}
package com.bms.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    private String refNumber;

    private PaymentStatus paymentStatus;
    private int amount;
    private String provider;
    private PaymentMode paymentMode;
}


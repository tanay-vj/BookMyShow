package com.bms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Booking extends BaseModel{

    private BookingStatus bookingStatus;
    @OneToMany
    private List<ShowSeat> showSeats;
    private int billingAmount;
    @OneToMany
    private List<Payment> payments;


}

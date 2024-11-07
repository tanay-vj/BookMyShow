package com.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeatType extends BaseModel{

    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;

    private int price;

}

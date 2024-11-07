package com.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
public class ShowSeat extends BaseModel{

    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;

    @UpdateTimestamp
    private Instant lockedAt;


    private ShowSeatStatus showSeatStatus;
}

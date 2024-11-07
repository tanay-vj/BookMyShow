package com.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "shows")
@Getter
@Setter
public class Show extends BaseModel {

    private Date startTime;
    private Date endTime;
    private String movie;

    @OneToOne
    private Screen screen;
}
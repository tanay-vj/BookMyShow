package com.bms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Screen extends BaseModel{

    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

}

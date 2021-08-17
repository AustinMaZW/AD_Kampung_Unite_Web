package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HitcherDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate prefDate;
    private LocalTime prefPickupTimeFrom;
    private String prefPickupLocation;
    @OneToMany(mappedBy = "hitcherDetail")
    private List<HitchRequest> hitchRequestList;
}

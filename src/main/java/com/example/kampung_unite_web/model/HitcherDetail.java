package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private int id;
    private LocalDate prefDate;
    private LocalTime prefPickupTimeFrom;
    private String prefPickupLocation;
    @OneToMany(mappedBy = "hitcherDetail")
    private List<HitchRequest> hitchRequestList;
}

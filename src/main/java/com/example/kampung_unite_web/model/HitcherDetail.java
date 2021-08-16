package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime prefPickupTimeFrom;   //calculate pickupTimeTo with this attribute. E.g. prefPickupTime + 3
    private String prefPickupLocation;
    @OneToMany(mappedBy = "hitcherDetail")
    private List<HitchRequest> hitchRequests;
    @OneToOne(mappedBy = "hitcherDetail")
    private GroceryList groceryList;
}

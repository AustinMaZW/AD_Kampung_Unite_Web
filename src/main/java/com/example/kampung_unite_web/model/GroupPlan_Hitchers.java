package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupPlan_Hitchers {
    @Id
    private int Id;
    private LocalDateTime pickupTimeChosen;
    private boolean buyerConfirmTransaction;
    private boolean hitcherConfirmTransaction;
    private RequestStatus requestStatus;
    @ManyToOne
    private GroupPlan groupPlan;
    @ManyToOne
    private HitcherDetail hitcherDetail;
}

package com.example.kampung_unite_web.model;

import com.example.kampung_unite_web.model.enums.RequestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HitchRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

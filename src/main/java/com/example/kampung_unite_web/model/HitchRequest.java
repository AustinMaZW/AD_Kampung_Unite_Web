package com.example.kampung_unite_web.model;

import com.example.kampung_unite_web.model.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int id;
    private LocalDateTime pickupTimeChosen;
    private boolean buyerConfirmTransaction;
    private boolean hitcherConfirmTransaction;
    private RequestStatus requestStatus;
    @ManyToOne
    @JsonIgnoreProperties("hitchRequests")
    private GroupPlan groupPlan;
    @ManyToOne
    @JsonIgnoreProperties("hitchRequests")
    private HitcherDetail hitcherDetail;

    public HitchRequest(LocalDateTime pickupTimeChosen, boolean buyerConfirmTransaction, boolean hitcherConfirmTransaction, RequestStatus requestStatus, GroupPlan groupPlan, HitcherDetail hitcherDetail) {
        this.pickupTimeChosen = pickupTimeChosen;
        this.buyerConfirmTransaction = buyerConfirmTransaction;
        this.hitcherConfirmTransaction = hitcherConfirmTransaction;
        this.requestStatus = requestStatus;
        this.groupPlan = groupPlan;
        this.hitcherDetail = hitcherDetail;
    }
}

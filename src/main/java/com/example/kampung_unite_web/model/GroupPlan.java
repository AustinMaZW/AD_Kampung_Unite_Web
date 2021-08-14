package com.example.kampung_unite_web.model;

import com.example.kampung_unite_web.model.enums.GroupPlanStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupPlan {
    @Id
    private int Id;
    private String storeName;
    private LocalDate shoppingDate;
    private String pickupAddress;
    private LocalDate pickupDate;
    private GroupPlanStatus groupPlanStatus;

    @OneToOne
    private CombinedPurchaseList combinedPurchaseList;

    @OneToMany(mappedBy = "groupPlanAT")
    private List<AvailableTime> availableTimes;

    @OneToMany(mappedBy = "groupPlanGL")
    private List<GroceryList> groceryLists;

    @OneToMany(mappedBy = "groupPlan")
    private List<HitchRequest> groupPlan_hitchers;
}

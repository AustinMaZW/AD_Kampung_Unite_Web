package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany(mappedBy = "groupPlan")
    private List<GroupPlan_Hitchers> groupPlan_hitchers;
}

package com.example.kampung_unite_web.implementations;

import com.example.kampung_unite_web.Interfaces.CPListService;
import com.example.kampung_unite_web.model.CombinedPurchaseList;
import com.example.kampung_unite_web.repo.CPLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CPListImplementation implements CPListService
{
    @Autowired
    CPLRepository cplrepo;

    @Override
    public List<CombinedPurchaseList> findShoppingListByGroupPlanId(int groupPlanId) {
        List<CombinedPurchaseList> cplList = cplrepo.findCombinedPurchaseListsByGroupPlanId(groupPlanId);
        return cplList;
    }
}

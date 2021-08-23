package com.example.kampung_unite_web.implementations;

import com.example.kampung_unite_web.service.CPListService;
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
    public List<CombinedPurchaseList> findAll() {
        return cplrepo.findAll();
    }

    @Override
    public List<CombinedPurchaseList> findShoppingListByGroupPlanId(int groupPlanId) {
        List<CombinedPurchaseList> cplList = cplrepo.findCombinedPurchaseListsByGroupPlanId(groupPlanId);
        return cplList;
    }

    @Override
    public CombinedPurchaseList findCPLById(int id) {
        CombinedPurchaseList dbcpl = null;
        if (cplrepo.findById(id).isPresent()){
            dbcpl = cplrepo.findById(id).get();
        }
        return dbcpl;
    }

    @Override
    public void updateCPL(CombinedPurchaseList combinedPurchaseList) {
        cplrepo.save(combinedPurchaseList);
    }

    @Override
    public void updateList(List<CombinedPurchaseList> cplList) {
        cplrepo.saveAll(cplList);
    }
}

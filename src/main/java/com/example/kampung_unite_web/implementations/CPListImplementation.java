package com.example.kampung_unite_web.implementations;

import com.example.kampung_unite_web.Interfaces.CPListService;
import com.example.kampung_unite_web.model.CombinedPurchaseList;
import com.example.kampung_unite_web.repo.CPLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public boolean saveAll(List<CombinedPurchaseList> list) {
        int size = cplrepo.saveAll(list).size();
        if (size == list.size())
            return true;
        else
            return false;
    }
}

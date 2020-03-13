package service;


import common.ServiceObjectResponse;
import entity.PurchaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IPurchaseRepository;
import services.IPurchaseService;

import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    IPurchaseRepository _purchaseRepository;
    public ServiceObjectResponse<List<PurchaseEntity>> getAll()
    {
        ServiceObjectResponse<List<PurchaseEntity>> response = new ServiceObjectResponse<>();

        try
        {
            List<PurchaseEntity> purchaseEntities = _purchaseRepository.getAll();

            response.setObject(purchaseEntities);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }


    public ServiceObjectResponse<PurchaseEntity> getById(int id)
    {
        ServiceObjectResponse<PurchaseEntity> response = new ServiceObjectResponse<>();

        try
        {
            PurchaseEntity purchaseEntity = _purchaseRepository.getById(id);

            response.setObject(purchaseEntity);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public ServiceObjectResponse<PurchaseEntity> create(PurchaseEntity purchaseEntity)
    {
        ServiceObjectResponse<PurchaseEntity> response = new ServiceObjectResponse<>();

        try
        {
            PurchaseEntity data = _purchaseRepository.PurchaseCreate(purchaseEntity);

            response.setObject(data);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}

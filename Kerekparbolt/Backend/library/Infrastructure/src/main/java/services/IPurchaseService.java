package services;

import common.ServiceObjectResponse;
import entity.PurchaseEntity;

import java.util.List;

public interface IPurchaseService {
    ServiceObjectResponse<List<PurchaseEntity>> getAll();
    ServiceObjectResponse<PurchaseEntity> getById(int id);
    ServiceObjectResponse<PurchaseEntity> create(PurchaseEntity purchaseEntity);
}

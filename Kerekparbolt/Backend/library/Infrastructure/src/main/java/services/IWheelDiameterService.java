package services;

import common.ServiceObjectResponse;
import entity.WheelDiameterEntity;

import java.util.List;

public interface IWheelDiameterService {

    ServiceObjectResponse<List<WheelDiameterEntity>> getAll();
    ServiceObjectResponse<WheelDiameterEntity> getById(int id);
    ServiceObjectResponse<WheelDiameterEntity> create(WheelDiameterEntity wheelDiameterEntity);
    ServiceObjectResponse<WheelDiameterEntity> update(WheelDiameterEntity wheelDiameterEntity);
    ServiceObjectResponse delete(int id);
}

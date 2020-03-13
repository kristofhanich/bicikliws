package services;

import common.ServiceObjectResponse;
import entity.BicycleEntity;
import response.BicycleResponse;

import java.util.List;

public interface IBicycleService {
    ServiceObjectResponse<List<BicycleEntity>> getAll();
    ServiceObjectResponse<BicycleEntity> getByItemNumber(String cikkszam);
    ServiceObjectResponse<BicycleEntity> getById(int id);
    ServiceObjectResponse<BicycleEntity> create(BicycleEntity bicycleEntity);
    ServiceObjectResponse<BicycleEntity> update(BicycleEntity bicycleEntity);
    ServiceObjectResponse delete(int id);
    ServiceObjectResponse<List<BicycleResponse>> getAllData();
    ServiceObjectResponse<BicycleResponse> getDataByItemNumber(String cikkszam);
    ServiceObjectResponse<BicycleResponse> getDataById(int id);
}

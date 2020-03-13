package services;

import common.ServiceObjectResponse;
import entity.BrandEntity;

import java.util.List;

public interface IBrandService {
    ServiceObjectResponse<List<BrandEntity>> getAll();
    ServiceObjectResponse<BrandEntity> getById(int id);
    ServiceObjectResponse<BrandEntity> create(BrandEntity brandEntity);
    ServiceObjectResponse<BrandEntity> update(BrandEntity brandEntity);
    ServiceObjectResponse delete(int id);
}

package services;

import common.ServiceObjectResponse;
import entity.SizeEntity;

import java.util.List;

public interface ISizeService {
    ServiceObjectResponse<List<SizeEntity>> getAll();
    ServiceObjectResponse<SizeEntity> getById(int id);
    ServiceObjectResponse<SizeEntity> create(SizeEntity sizeEntity);
    ServiceObjectResponse<SizeEntity> update(SizeEntity sizeEntity);
    ServiceObjectResponse delete(int id);
}

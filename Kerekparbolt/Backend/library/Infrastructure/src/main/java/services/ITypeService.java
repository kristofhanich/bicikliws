package services;

import common.ServiceObjectResponse;
import entity.TypeEntity;

import java.util.List;

public interface ITypeService {

    ServiceObjectResponse<List<TypeEntity>> getAll();
    ServiceObjectResponse<TypeEntity> getById(int id);
    ServiceObjectResponse<TypeEntity> create(TypeEntity typeEntity);
    ServiceObjectResponse<TypeEntity> update(TypeEntity typeEntity);
    ServiceObjectResponse delete(int id);
}

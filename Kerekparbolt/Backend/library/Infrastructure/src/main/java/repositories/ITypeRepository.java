package repositories;

import entity.TypeEntity;

import java.util.List;

public interface ITypeRepository {

    TypeEntity TypeCreate(TypeEntity typeEntity) throws Exception;
    TypeEntity update(TypeEntity typeEntity) throws Exception;
    boolean delete(int id) throws Exception;
    List<TypeEntity> getAll() throws Exception;
    TypeEntity getById(int id) throws Exception;
}

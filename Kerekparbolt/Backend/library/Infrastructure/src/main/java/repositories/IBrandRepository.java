package repositories;

import entity.BrandEntity;

import java.util.List;

public interface IBrandRepository {
    BrandEntity BrandCreate(BrandEntity brandEntity) throws Exception;
    BrandEntity update(BrandEntity brandEntity) throws Exception;
    boolean delete(int id) throws Exception;
    List<BrandEntity> getAll() throws Exception;
    BrandEntity getById(int id) throws Exception;
}

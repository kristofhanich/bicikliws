package repositories;

import entity.SizeEntity;

import java.util.List;

public interface ISizeRepository {
    SizeEntity SizeCreate(SizeEntity sizeEntity) throws Exception;
    SizeEntity update(SizeEntity sizeEntity) throws Exception;
    boolean delete(int id) throws Exception;
    List<SizeEntity> getAll() throws Exception;
    SizeEntity getById(int id) throws Exception;
}

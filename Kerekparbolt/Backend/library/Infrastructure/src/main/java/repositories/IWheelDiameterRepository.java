package repositories;

import entity.WheelDiameterEntity;

import java.util.List;

public interface IWheelDiameterRepository {
    WheelDiameterEntity WheelDiameterCreate(WheelDiameterEntity wheelDiameterEntity) throws Exception;
    WheelDiameterEntity update(WheelDiameterEntity wheelDiameterEntity) throws Exception;
    boolean delete(int id) throws Exception;
    List<WheelDiameterEntity> getAll() throws Exception;
    WheelDiameterEntity getById(int id) throws Exception;
}

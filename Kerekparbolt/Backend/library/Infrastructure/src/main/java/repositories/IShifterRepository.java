package repositories;

import entity.ShifterEntity;

import java.util.List;

public interface IShifterRepository {
    ShifterEntity ShifterCreate(ShifterEntity shifterEntity) throws Exception;
    List<ShifterEntity> getAll() throws Exception;
    ShifterEntity getById(int id) throws Exception;
}

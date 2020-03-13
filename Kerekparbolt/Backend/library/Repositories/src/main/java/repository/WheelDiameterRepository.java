package repository;

import common.DBConnection;
import entity.WheelDiameterEntity;
import org.springframework.stereotype.Service;
import repositories.IWheelDiameterRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WheelDiameterRepository implements IWheelDiameterRepository {

    public WheelDiameterEntity WheelDiameterCreate(WheelDiameterEntity wheelDiameterEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL WheelDiameterCreate(?, ?) }";

        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt(1, wheelDiameterEntity.Id);
        stmt.setString(2, wheelDiameterEntity.Name);



        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            wheelDiameterEntity.Id = resultSets.getInt(1);
        }


        return wheelDiameterEntity;
    }

    public WheelDiameterEntity update(WheelDiameterEntity wheelDiameterEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL WheelDiameterUpdate(?, ?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", wheelDiameterEntity.Id);
        stmt.setString("paramSize", wheelDiameterEntity.Name);

        int affectedRows  = stmt.executeUpdate();

        //ellenorizzuk, hogy van e modositott record
        if (affectedRows == 0)
        {
            throw new SQLException("A rekord modositasa sikertelen volt.");
        }

        return  wheelDiameterEntity;
    }

    public boolean delete(int id) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL WheelDiameterDelete(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        int affectedRows  = stmt.executeUpdate();

        return  affectedRows == 1 ? true : false;
    }

    public List<WheelDiameterEntity> getAll() throws Exception
    {
        List<WheelDiameterEntity> wheelDiameterEntities = new ArrayList<>();
        WheelDiameterEntity wheelDiameterEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL WheelDiameterGetAll() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            wheelDiameterEntity = MapWheelDiameter(resultSets);
            wheelDiameterEntities.add(wheelDiameterEntity);
        }

        return wheelDiameterEntities;
    }

    public WheelDiameterEntity getById(int id) throws Exception
    {
        WheelDiameterEntity wheelDiameterEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL WheelDiameterGetById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            wheelDiameterEntity = MapWheelDiameter(resultSets);

        }

        return  wheelDiameterEntity;
    }




    private WheelDiameterEntity MapWheelDiameter(ResultSet dataSet) throws SQLException
    {
        WheelDiameterEntity wheelDiameterEntity = new WheelDiameterEntity();
        wheelDiameterEntity.Id = Integer.parseInt(dataSet.getString("Id"));
        wheelDiameterEntity.Name = dataSet.getString("meret");

        return  wheelDiameterEntity;
    }



}

package repository;

import common.DBConnection;
import entity.BrandEntity;
import org.springframework.stereotype.Service;
import repositories.IBrandRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandRepository implements IBrandRepository {

    public BrandEntity BrandCreate(BrandEntity brandEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BrandCreate(?, ?) }";

        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt(1, brandEntity.Id);
        stmt.setString(2, brandEntity.Name);


        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            brandEntity.Id = resultSets.getInt(1);
        }


        return brandEntity;
    }

    public BrandEntity update(BrandEntity brandEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BrandUpdate(?, ?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", brandEntity.Id);
        stmt.setString("paramMarka", brandEntity.Name);


        int affectedRows  = stmt.executeUpdate();

        //ellenorizzuk, hogy van e modositott record
        if (affectedRows == 0)
        {
            throw new SQLException("A rekord modositasa sikertelen volt.");
        }

        return  brandEntity;
    }

    public boolean delete(int id) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BrandDelete(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        int affectedRows  = stmt.executeUpdate();

        return  affectedRows == 1 ? true : false;
    }

    public List<BrandEntity> getAll() throws Exception
    {
        List<BrandEntity> brandEntities = new ArrayList<>();
        BrandEntity brandEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BrandGetAll() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            brandEntity = MapBrand(resultSets);
            brandEntities.add(brandEntity);
        }

        return brandEntities;
    }

    public BrandEntity getById(int id) throws Exception
    {
        BrandEntity brandEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BrandGetById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            brandEntity = MapBrand(resultSets);

        }

        return  brandEntity;
    }




    private BrandEntity MapBrand(ResultSet dataSet) throws SQLException
    {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.Id = Integer.parseInt(dataSet.getString("id"));
        brandEntity.Name = dataSet.getString("marka");

        return  brandEntity;
    }



}

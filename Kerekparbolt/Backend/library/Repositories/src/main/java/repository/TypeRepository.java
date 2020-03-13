package repository;

import common.DBConnection;
import entity.TypeEntity;
import org.springframework.stereotype.Service;
import repositories.ITypeRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TypeRepository implements ITypeRepository {

    public TypeEntity TypeCreate(TypeEntity typeEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL TypeCreate(?, ? }";

        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt(1, typeEntity.Id);
        stmt.setString(2, typeEntity.Name);


        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            typeEntity.Id = resultSets.getInt(1);
        }


        return typeEntity;
    }

    public TypeEntity update(TypeEntity typeEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL TypeUpdate(?, ?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", typeEntity.Id);
        stmt.setString("paramTipus", typeEntity.Name);


        int affectedRows  = stmt.executeUpdate();

        //ellenorizzuk, hogy van e modositott record
        if (affectedRows == 0)
        {
            throw new SQLException("A rekord modositasa sikertelen volt.");
        }

        return  typeEntity;
    }

    public boolean delete(int id) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL TypeDelete(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        int affectedRows  = stmt.executeUpdate();

        return  affectedRows == 1 ? true : false;
    }

    public List<TypeEntity> getAll() throws Exception
    {
        List<TypeEntity> typeEntities = new ArrayList<>();
        TypeEntity typeEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL TypeGetAll() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            typeEntity = MapType(resultSets);
            typeEntities.add(typeEntity);
        }

        return typeEntities;
    }

    public TypeEntity getById(int id) throws Exception
    {
        TypeEntity typeEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL TypeGetById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            typeEntity = MapType(resultSets);

        }

        return  typeEntity;
    }




    private TypeEntity MapType(ResultSet dataSet) throws SQLException
    {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.Id = Integer.parseInt(dataSet.getString("id"));
        typeEntity.Name = dataSet.getString("tipus");

        return  typeEntity;
    }



}

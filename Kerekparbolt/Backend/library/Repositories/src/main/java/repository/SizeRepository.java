package repository;

import common.DBConnection;
import entity.SizeEntity;
import org.springframework.stereotype.Service;
import repositories.ISizeRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SizeRepository implements ISizeRepository {

    public SizeEntity SizeCreate(SizeEntity sizeEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL FrameSizeCreate(?, ?) }";

        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt(1, sizeEntity.Id);
        stmt.setString(2, sizeEntity.Name);



        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            sizeEntity.Id = resultSets.getInt(1);
        }


        return sizeEntity;
    }

    public SizeEntity update(SizeEntity sizeEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL FrameSizeUpdate(?, ?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", sizeEntity.Id);
        stmt.setString("paramMeret", sizeEntity.Name);


        int affectedRows  = stmt.executeUpdate();

        //ellenorizzuk, hogy van e modositott record
        if (affectedRows == 0)
        {
            throw new SQLException("A rekord modositasa sikertelen volt.");
        }

        return  sizeEntity;
    }

    public boolean delete(int id) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL FrameSizeDelete(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        int affectedRows  = stmt.executeUpdate();

        return  affectedRows == 1 ? true : false;
    }

    public List<SizeEntity> getAll() throws Exception
    {
        List<SizeEntity> sizeEntities = new ArrayList<>();
        SizeEntity sizeEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL FrameSizeGetAll() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            sizeEntity = MapSize(resultSets);
            sizeEntities.add(sizeEntity);
        }

        return sizeEntities;
    }

    public SizeEntity getById(int id) throws Exception
    {
        SizeEntity sizeEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL FrameSizeGetById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramID", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            sizeEntity = MapSize(resultSets);

        }

        return  sizeEntity;
    }




    private SizeEntity MapSize(ResultSet dataSet) throws SQLException
    {
        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.Id = Integer.parseInt(dataSet.getString("id"));
        sizeEntity.Name = dataSet.getString("meret");
        return  sizeEntity;
    }



}

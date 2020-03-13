package repository;

import common.DBConnection;
import entity.BicycleEntity;
import org.springframework.stereotype.Service;
import repositories.IBicycleRepository;
import response.BicycleResponse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BicycleRepository implements IBicycleRepository {

    public BicycleEntity BicycleCreate(BicycleEntity bicycleEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleCreate(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt(1, bicycleEntity.Id);
        stmt.setString(2, bicycleEntity.Cikkszam);
        stmt.setInt(3, bicycleEntity.MarkaID);
        stmt.setInt(4, bicycleEntity.VazmeretID);
        stmt.setInt(5, bicycleEntity.FelniAtmeroID);
        stmt.setInt(6, bicycleEntity.ValtoTipus);
        stmt.setInt(7, bicycleEntity.TipusID);
        stmt.setInt(8, bicycleEntity.Ar);
        stmt.setString(9, bicycleEntity.URL);


        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            bicycleEntity.Id = resultSets.getInt(1);
        }


        return bicycleEntity;
    }

    public BicycleEntity update(BicycleEntity bicycleEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleUpdate(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", bicycleEntity.Id);
        stmt.setString("paramCikkszam", bicycleEntity.Cikkszam);
        stmt.setInt("paramMarkaId", bicycleEntity.MarkaID);
        stmt.setInt("paramVazmeretId", bicycleEntity.VazmeretID);
        stmt.setInt("paramFelniAtmeroId", bicycleEntity.FelniAtmeroID);
        stmt.setInt("paramValtoTipus", bicycleEntity.ValtoTipus);
        stmt.setInt("paramTipusId", bicycleEntity.TipusID);
        stmt.setInt("paramAr", bicycleEntity.Ar);
        stmt.setString("paramKep", bicycleEntity.URL);

        int affectedRows  = stmt.executeUpdate();

        //ellenorizzuk, hogy van e modositott record
        if (affectedRows == 0)
        {
            throw new SQLException("A rekord modositasa sikertelen volt.");
        }

        return  bicycleEntity;
    }

    public boolean delete(int id) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleDelete(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        int affectedRows  = stmt.executeUpdate();

        return  affectedRows == 1 ? true : false;
    }

    public List<BicycleEntity> getAll() throws Exception
    {
        List<BicycleEntity> bicycleEntities = new ArrayList<>();
        BicycleEntity bicycleEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleGetAll() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            bicycleEntity = MapBicycle(resultSets);
            bicycleEntities.add(bicycleEntity);
        }

        return bicycleEntities;
    }

    public BicycleEntity getById(int id) throws Exception
    {
        BicycleEntity bicycleEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleSelectById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramID", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            bicycleEntity = MapBicycle(resultSets);

        }

        return  bicycleEntity;
    }

    public BicycleEntity getByItemNumber(String cikkszam) throws Exception
    {
        BicycleEntity bicycleEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleSelectByItemNumber(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setString("paramCikkszam", cikkszam);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            bicycleEntity = MapBicycle(resultSets);
        }

        return  bicycleEntity;
    }

    public List<BicycleResponse> getAllData() throws Exception
    {

        List<BicycleResponse> bicycleResponses = new ArrayList<>();
        BicycleResponse bicycleResponse = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleGetAllData() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            bicycleResponse = MapBicycleResponse(resultSets);
            bicycleResponses.add(bicycleResponse);
        }

        return bicycleResponses;
    }

    public BicycleResponse getDataById(int id) throws Exception
    {
        BicycleResponse bicycleResponse = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleSelectDataById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            bicycleResponse = MapBicycleResponse(resultSets);

        }

        return  bicycleResponse;
    }

    public BicycleResponse getDataByItemNumber(String cikkszam) throws Exception
    {
        BicycleResponse bicycleResponse = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL BicycleSelectDataByItemNumber(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setString("paramCikkszam", cikkszam);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            bicycleResponse = MapBicycleResponse(resultSets);
        }

        return  bicycleResponse;
    }

    private BicycleResponse MapBicycleResponse(ResultSet dataSet) throws Exception
    {
        BicycleResponse bicycleResponse=new BicycleResponse();
        bicycleResponse.Marka=dataSet.getString("marka");
        bicycleResponse.Vazmeret=dataSet.getString("vazMeret");
        bicycleResponse.FelniAtmero=dataSet.getString("felniAtmero");
        bicycleResponse.Valto=dataSet.getString("valto");
        bicycleResponse.Tipus=dataSet.getString("tipus");
        BicycleEntity bicycleEntity = MapBicycle(dataSet);
        bicycleResponse.Id = bicycleEntity.Id;
        bicycleResponse.Cikkszam = bicycleEntity.Cikkszam;
        bicycleResponse.MarkaID = bicycleEntity.MarkaID;
        bicycleResponse.VazmeretID = bicycleEntity.VazmeretID;
        bicycleResponse.FelniAtmeroID = bicycleEntity.FelniAtmeroID;
        bicycleResponse.ValtoTipus = bicycleEntity.ValtoTipus;
        bicycleResponse.TipusID = bicycleEntity.TipusID;
        bicycleResponse.Ar = bicycleEntity.Ar;
        bicycleResponse.URL = bicycleEntity.URL;
        return bicycleResponse;
    }

    private BicycleEntity MapBicycle(ResultSet dataSet) throws SQLException
    {
        BicycleEntity bicycleEntity = new BicycleEntity();
        bicycleEntity.Id = Integer.parseInt(dataSet.getString("id"));
        bicycleEntity.Cikkszam = dataSet.getString("cikkszam");
        bicycleEntity.MarkaID = Integer.parseInt(dataSet.getString("markaId"));
        bicycleEntity.VazmeretID = Integer.parseInt(dataSet.getString("vazmeretId"));
        bicycleEntity.FelniAtmeroID = Integer.parseInt(dataSet.getString("felniAtmeroId"));
        bicycleEntity.ValtoTipus = Integer.parseInt(dataSet.getString("ValtoTipus"));
        bicycleEntity.TipusID = Integer.parseInt(dataSet.getString("tipusID"));
        bicycleEntity.Ar = Integer.parseInt(dataSet.getString("ar"));
        bicycleEntity.URL = dataSet.getString("kep");
        return  bicycleEntity;
    }



}

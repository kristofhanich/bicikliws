package repository;

import common.DBConnection;
import entity.PurchaseEntity;
import enums.Payment;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import repositories.IPurchaseRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseRepository implements IPurchaseRepository {

    public PurchaseEntity PurchaseCreate(PurchaseEntity purchaseEntity) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL PurchaseCreate(?, ?, ?, ?, ?) }";

        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt(1, purchaseEntity.Id);
        stmt.setString(2, purchaseEntity.CustomerUniqId);
        stmt.setInt(3, purchaseEntity.ItemId);
        stmt.setString(4, String.valueOf(purchaseEntity.Date));
        stmt.setString(5, String.valueOf(purchaseEntity.PaymentMethod));



        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            purchaseEntity.Id = resultSets.getInt(1);
        }


        return purchaseEntity;
    }





    public List<PurchaseEntity> getAll() throws Exception
    {
        List<PurchaseEntity> purchaseEntities = new ArrayList<>();
        PurchaseEntity purchaseEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL PurchaseGetAll() }";
        CallableStatement stmt = connection.prepareCall(SQL);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            purchaseEntity = MapPurchase(resultSets);
            purchaseEntities.add(purchaseEntity);
        }

        return purchaseEntities;
    }

    public PurchaseEntity getById(int id) throws Exception
    {
        PurchaseEntity purchaseEntity = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL PurchaseGetById(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setInt("paramId", id);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            purchaseEntity = MapPurchase(resultSets);

        }

        return  purchaseEntity;
    }

    private PurchaseEntity MapPurchase(ResultSet dataSet) throws SQLException
    {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.Id = Integer.parseInt(dataSet.getString("Id"));
        purchaseEntity.CustomerUniqId = dataSet.getString("vasarloUniqId");
        purchaseEntity.ItemId = Integer.parseInt(dataSet.getString("termekKod"));
        purchaseEntity.Date = DateTime.parse(dataSet.getString("termekkod"));
        purchaseEntity.PaymentMethod = Payment.valueOf(dataSet.getString("fizetesiMod"));

        return  purchaseEntity;
    }



}

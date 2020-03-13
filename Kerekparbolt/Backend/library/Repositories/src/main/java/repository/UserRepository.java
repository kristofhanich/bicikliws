package repository;

import common.DBConnection;
import entity.UserEntity;
import org.springframework.stereotype.Service;
import repositories.IUserRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserRepository implements IUserRepository
{
    public UserEntity create(UserEntity user) throws Exception
    {
        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL UserCreate(?, ?, ?, ?, ?) }";

        CallableStatement  stmt = connection.prepareCall(SQL);
        stmt.setInt(1, user.Id);
        stmt.setString(2, user.UniqID);
        stmt.setString(3, user.Email);
        stmt.setString(4, user.Jelszo);
        stmt.setString(5, user.Role);


        ResultSet resultSets  = stmt.executeQuery();
        if (resultSets.next())
        {
            user.Id = resultSets.getInt(1);
        }


        return  user;
    }

    public UserEntity findByUniqID(String uniqID) throws Exception
    {
        UserEntity user = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL UserFindByUniqID(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setString("paramUniqId", uniqID);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            user = MapUser(resultSets);
        }

        return  user;
    }

    public UserEntity findByCredentials(String email, String jelszo) throws Exception
    {
        UserEntity user = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL UserFindByCredentials(?, ?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setString("paramEmail", email);
        stmt.setString("paramJelszo", jelszo);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            user = MapUser(resultSets);
        }

        return  user;
    }

    @Override
    public UserEntity findByEmail(String email) throws Exception
    {
        UserEntity user = null;

        Connection connection = DBConnection.getConnection();

        String SQL = "{ CALL UserFindByEmail(?) }";
        CallableStatement stmt = connection.prepareCall(SQL);
        stmt.setString("paramEmail", email);

        ResultSet resultSets = stmt.executeQuery();

        // Iterate through the data in the result set.
        while (resultSets.next())
        {
            user = MapUser(resultSets);
        }

        return  user;
    }

    private UserEntity MapUser(ResultSet dataSet) throws SQLException
    {
        UserEntity user = new UserEntity();
        user.Id = Integer.parseInt(dataSet.getString("Id"));
        user.UniqID = dataSet.getString("UniqID");
        user.Email = dataSet.getString("Email");
        user.Jelszo = dataSet.getString("Jelszo");
        user.Role = dataSet.getString("Role");

        return  user;
    }
}

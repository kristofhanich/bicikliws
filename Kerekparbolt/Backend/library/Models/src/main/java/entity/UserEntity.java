package entity;

import io.swagger.annotations.ApiModel;
import request.RegisterRequest;

@ApiModel
public class UserEntity
{
    public int Id;
    public String UniqID;
    public String Email;
    public String Jelszo;
    public String Role;

    public UserEntity() {
    }

    public UserEntity(int id, String uniqID, String email, String jelszo, String role ) {
        Id = id;
        UniqID = uniqID;
        Email = email;
        Jelszo = jelszo;
        Role = role;
    }

    public UserEntity(RegisterRequest data)
    {
        Email = data.Email;
        Jelszo =  data.Jelszo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUniqID() {
        return UniqID;
    }

    public void setUniqID(String uniqID) {
        UniqID = uniqID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getJelszo() {
        return Jelszo;
    }

    public void setJelszo(String jelszo) {
        Jelszo = jelszo;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}

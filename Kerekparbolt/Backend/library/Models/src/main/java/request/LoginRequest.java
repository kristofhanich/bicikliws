package request;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ApiModel
public class LoginRequest
{
    @NotBlank(message = "Email is mandatory")
    @NotEmpty(message = "Email can't be empty")
    public String Email;

    @NotBlank(message = "Jelszo is mandatory")
    @NotEmpty(message = "Jelszo can't be empty")
    public String Jelszo;

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getJelszo()
    {
        return Jelszo;
    }

    public void setJelszo(String jelszo)
    {
        Jelszo = jelszo;
    }
}

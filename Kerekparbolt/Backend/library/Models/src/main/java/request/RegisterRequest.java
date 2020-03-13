package request;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ApiModel
public class RegisterRequest
{
    @NotBlank(message = "Email is mandatory")
    @NotEmpty(message = "Email can't be empty")
    public String Email;

    @NotBlank(message = "Jelszo is mandatory")
    @NotEmpty(message = "Jelszo can't be empty")
    public String Jelszo;
}

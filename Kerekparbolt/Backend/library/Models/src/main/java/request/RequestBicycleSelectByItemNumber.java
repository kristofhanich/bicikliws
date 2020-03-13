package request;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ApiModel
public class RequestBicycleSelectByItemNumber {
    @NotBlank(message = "Item number is mandatory")
    @NotEmpty(message = "Item number can't be empty")
    public String Cikkszam;
}

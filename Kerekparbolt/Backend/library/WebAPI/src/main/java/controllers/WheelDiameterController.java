package controllers;

import common.ServiceObjectResponse;
import entity.WheelDiameterEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.IWheelDiameterService;

import java.util.List;

@RestController
@Api(tags = {"wheelDiameter"}, value = "WheelDiameterService")
public class WheelDiameterController {

    @Autowired
    IWheelDiameterService _wheelDiameterService;

    @ApiOperation(value = "all", nickname = "all")
    @GetMapping("/api/wheelDiameter/all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<WheelDiameterEntity> GetAll() throws Exception
    {
        ServiceObjectResponse<List<WheelDiameterEntity>> request = _wheelDiameterService.getAll();

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "create", nickname = "create")
    @PostMapping("/api/wheelDiameter")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public WheelDiameterEntity CreateWheelDiameter(@RequestBody WheelDiameterEntity wheelDiameterEntity) throws Exception
    {
        ServiceObjectResponse<WheelDiameterEntity> request = _wheelDiameterService.create(wheelDiameterEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }


    @ApiOperation(value = "update", nickname = "update")
    @PutMapping("/api/wheelDiameter")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public WheelDiameterEntity UpdateWheelDiameter(@RequestBody WheelDiameterEntity wheelDiameterEntity) throws Exception
    {
        ServiceObjectResponse<WheelDiameterEntity> request = _wheelDiameterService.update(wheelDiameterEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "delete", nickname = "delete")
    @DeleteMapping("/api/wheelDiameter/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public boolean DeleteWheelDiameter(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse request = _wheelDiameterService.delete(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getIsSuccess();
    }

    @ApiOperation(value = "getById", nickname = "getById")
    @GetMapping("/api/wheelDiameter/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public WheelDiameterEntity GetWheelDiameterById(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse<WheelDiameterEntity> request = _wheelDiameterService.getById(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }
}

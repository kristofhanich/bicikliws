package controllers;

import common.ServiceObjectResponse;
import entity.BicycleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import request.RequestBicycleSelectByItemNumber;
import response.BicycleResponse;
import services.IBicycleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = {"bicycle"}, value = "BicycleService")
public class BicycleController {

    @Autowired
    IBicycleService _bicycleService;


    @ApiOperation(value = "all", nickname = "all")
    @GetMapping("/api/bicycle/all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<BicycleEntity> GetAll() throws Exception
    {
        ServiceObjectResponse<List<BicycleEntity>> request = _bicycleService.getAll();

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "getByItemNumber", nickname = "getByItemNumber")
    @PostMapping("/api/bicycle/byItemNumber")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BicycleEntity GetBicycleByItemNumber(@RequestBody @Valid RequestBicycleSelectByItemNumber data) throws Exception
    {
        ServiceObjectResponse<BicycleEntity> request = _bicycleService.getByItemNumber(data.Cikkszam);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "create", nickname = "create")
    @PostMapping("/api/bicycle")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BicycleEntity CreateBicycle(@RequestBody BicycleEntity bicycleEntity) throws Exception
    {
        ServiceObjectResponse<BicycleEntity> request = _bicycleService.create(bicycleEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "update", nickname = "update")
    @PutMapping("/api/bicycle")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BicycleEntity UpdateBicycle(@RequestBody BicycleEntity bicycleEntity) throws Exception
    {
        ServiceObjectResponse<BicycleEntity> request = _bicycleService.update(bicycleEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "delete", nickname = "delete")
    @DeleteMapping("/api/bicycle/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public boolean DeleteBicycle(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse request = _bicycleService.delete(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getIsSuccess();
    }

    @ApiOperation(value = "getById", nickname = "getById")
    @GetMapping("/api/bicycle/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BicycleEntity GetBicycleById(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse<BicycleEntity> request = _bicycleService.getById(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "allData", nickname = "allData")
    @GetMapping("/api/bicycle/data/all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<BicycleResponse> GetAllData() throws Exception
    {
        ServiceObjectResponse<List<BicycleResponse>> request = _bicycleService.getAllData();

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "getDataByItemNumber", nickname = "getDataByItemNumber")
    @PostMapping("/api/bicycle/data/byItemNumber")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BicycleResponse GetBicycleDataByItemNumber(@RequestBody @Valid RequestBicycleSelectByItemNumber data) throws Exception
    {
        ServiceObjectResponse<BicycleResponse> request = _bicycleService.getDataByItemNumber(data.Cikkszam);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "getDataById", nickname = "getDataById")
    @GetMapping("/api/bicycle/data/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BicycleResponse GetBicycleDataById(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse<BicycleResponse> request = _bicycleService.getDataById(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }
}

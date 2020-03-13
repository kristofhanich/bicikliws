package controllers;

import common.ServiceObjectResponse;
import entity.TypeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.ITypeService;

import java.util.List;

@RestController
@Api(tags = {"type"}, value = "TypeService")
public class TypeController {

    @Autowired
    ITypeService _typeService;


    @ApiOperation(value = "all", nickname = "all")
    @GetMapping("/api/type/all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<TypeEntity> GetAll() throws Exception
    {
        ServiceObjectResponse<List<TypeEntity>> request = _typeService.getAll();

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "create", nickname = "create")
    @PostMapping("/api/type")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public TypeEntity CreateType(@RequestBody TypeEntity typeEntity) throws Exception
    {
        ServiceObjectResponse<TypeEntity> request = _typeService.create(typeEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }


    @ApiOperation(value = "update", nickname = "update")
    @PutMapping("/api/type")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public TypeEntity UpdateType(@RequestBody TypeEntity typeEntity) throws Exception
    {
        ServiceObjectResponse<TypeEntity> request = _typeService.update(typeEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "delete", nickname = "delete")
    @DeleteMapping("/api/type/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public boolean DeleteBrand(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse request = _typeService.delete(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getIsSuccess();
    }

    @ApiOperation(value = "getById", nickname = "getById")
    @GetMapping("/api/type/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public TypeEntity GetTypeById(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse<TypeEntity> request = _typeService.getById(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }
}

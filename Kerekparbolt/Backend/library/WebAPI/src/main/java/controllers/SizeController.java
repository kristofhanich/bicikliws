package controllers;

import common.ServiceObjectResponse;
import entity.SizeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.ISizeService;

import java.util.List;

@RestController
@Api(tags = {"size"}, value = "SizeService")
public class SizeController {

    @Autowired
    ISizeService _sizeService;

    @ApiOperation(value = "all", nickname = "all")
    @GetMapping("/api/size/all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<SizeEntity> GetAll() throws Exception
    {
        ServiceObjectResponse<List<SizeEntity>> request = _sizeService.getAll();

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "create", nickname = "create")
    @PostMapping("/api/size")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public SizeEntity CreateSize(@RequestBody SizeEntity sizeEntity) throws Exception
    {
        ServiceObjectResponse<SizeEntity> request = _sizeService.create(sizeEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }


    @ApiOperation(value = "update", nickname = "update")
    @PutMapping("/api/size")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public SizeEntity UpdateSize(@RequestBody SizeEntity sizeEntity) throws Exception
    {
        ServiceObjectResponse<SizeEntity> request = _sizeService.update(sizeEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "delete", nickname = "delete")
    @DeleteMapping("/api/size/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public boolean DeleteSize(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse request = _sizeService.delete(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getIsSuccess();
    }

    @ApiOperation(value = "getById", nickname = "getById")
    @GetMapping("/api/size/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public SizeEntity GetSizeById(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse<SizeEntity> request = _sizeService.getById(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }
}

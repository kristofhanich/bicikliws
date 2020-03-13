package controllers;


import common.ServiceObjectResponse;
import entity.BrandEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.IBrandService;

import java.util.List;

@RestController
@Api(tags = {"brand"}, value = "BrandService")
public class BrandController {

    @Autowired
    IBrandService _brandService;

    @ApiOperation(value = "all", nickname = "all")
    @GetMapping("/api/brand/all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<BrandEntity> GetAll() throws Exception
    {
        ServiceObjectResponse<List<BrandEntity>> request = _brandService.getAll();

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "create", nickname = "create")
    @PostMapping("/api/brand")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BrandEntity CreateBrand(@RequestBody BrandEntity brandEntity) throws Exception
    {
        ServiceObjectResponse<BrandEntity> request = _brandService.create(brandEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }


    @ApiOperation(value = "update", nickname = "update")
    @PutMapping("/api/brand")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BrandEntity UpdateBrand(@RequestBody BrandEntity brandEntity) throws Exception
    {
        ServiceObjectResponse<BrandEntity> request = _brandService.update(brandEntity);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }

    @ApiOperation(value = "delete", nickname = "delete")
    @DeleteMapping("/api/brand/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public boolean DeleteBrand(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse request = _brandService.delete(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getIsSuccess();
    }

    @ApiOperation(value = "getById", nickname = "getById")
    @GetMapping("/api/brand/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public BrandEntity GetBrandById(@PathVariable int id) throws Exception
    {
        ServiceObjectResponse<BrandEntity> request = _brandService.getById(id);

        if(!request.getIsSuccess())
        {
            throw new Exception(request.getMessage());
        }
        return request.getObject();
    }
}

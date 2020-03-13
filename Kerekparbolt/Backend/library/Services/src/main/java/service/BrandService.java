package service;

import common.ServiceObjectResponse;
import entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IBrandRepository;
import services.IBrandService;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    @Autowired
    IBrandRepository _brandRepository;

    public ServiceObjectResponse<List<BrandEntity>> getAll()
    {
        ServiceObjectResponse<List<BrandEntity>> response = new ServiceObjectResponse<>();

        try
        {
            List<BrandEntity> brandEntities = _brandRepository.getAll();

            BrandEntity emptySelect = new BrandEntity(0, "");
            brandEntities.add(0, emptySelect);

            response.setObject(brandEntities);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }


    public ServiceObjectResponse<BrandEntity> getById(int id)
    {
        ServiceObjectResponse<BrandEntity> response = new ServiceObjectResponse<>();

        try
        {
            BrandEntity brandEntity = _brandRepository.getById(id);

            response.setObject(brandEntity);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public ServiceObjectResponse<BrandEntity> create(BrandEntity brandEntity)
    {
        ServiceObjectResponse<BrandEntity> response = new ServiceObjectResponse<>();

        try
        {
            BrandEntity data = _brandRepository.BrandCreate(brandEntity);

            response.setObject(data);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public ServiceObjectResponse<BrandEntity> update(BrandEntity brandEntity)
    {
        ServiceObjectResponse<BrandEntity> response = new ServiceObjectResponse<>();

        try
        {
            BrandEntity data = _brandRepository.update(brandEntity);

            response.setObject(data);
            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public ServiceObjectResponse delete(int id)
    {
        ServiceObjectResponse response = new ServiceObjectResponse();

        try
        {
            boolean success = _brandRepository.delete(id);

            if(!success)
            {
                throw new Exception("Record not deleted (id: " + id + ").");
            }

            response.setIsSuccess(true);
            response.setMessage("No errors.");
        }
        catch (Exception ex)
        {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}

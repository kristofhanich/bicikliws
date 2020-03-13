package service;

import common.ServiceObjectResponse;
import entity.SizeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ISizeRepository;
import services.ISizeService;

import java.util.List;

@Service
public class SizeService implements ISizeService {

    @Autowired
    ISizeRepository _sizeRepository;

    public ServiceObjectResponse<List<SizeEntity>> getAll()
    {
        ServiceObjectResponse<List<SizeEntity>> response = new ServiceObjectResponse<>();

        try
        {
            List<SizeEntity> sizeEntities = _sizeRepository.getAll();
            SizeEntity emptySelect = new SizeEntity(0, "");
            sizeEntities.add(0, emptySelect);
            response.setObject(sizeEntities);
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


    public ServiceObjectResponse<SizeEntity> getById(int id)
    {
        ServiceObjectResponse<SizeEntity> response = new ServiceObjectResponse<>();

        try
        {
            SizeEntity sizeEntity = _sizeRepository.getById(id);

            response.setObject(sizeEntity);
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

    public ServiceObjectResponse<SizeEntity> create(SizeEntity sizeEntity)
    {
        ServiceObjectResponse<SizeEntity> response = new ServiceObjectResponse<>();

        try
        {
            SizeEntity data = _sizeRepository.SizeCreate(sizeEntity);

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

    public ServiceObjectResponse<SizeEntity> update(SizeEntity sizeEntity)
    {
        ServiceObjectResponse<SizeEntity> response = new ServiceObjectResponse<>();

        try
        {
            SizeEntity data = _sizeRepository.update(sizeEntity);

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
            boolean success = _sizeRepository.delete(id);

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

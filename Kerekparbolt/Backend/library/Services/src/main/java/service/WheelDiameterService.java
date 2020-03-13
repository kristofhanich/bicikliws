package service;


import common.ServiceObjectResponse;
import entity.WheelDiameterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IWheelDiameterRepository;
import services.IWheelDiameterService;

import java.util.List;

@Service
public class WheelDiameterService implements IWheelDiameterService {

    @Autowired
    IWheelDiameterRepository _wheelDiameterRepository;
    public ServiceObjectResponse<List<WheelDiameterEntity>> getAll()
    {
        ServiceObjectResponse<List<WheelDiameterEntity>> response = new ServiceObjectResponse<>();

        try
        {
            List<WheelDiameterEntity> wheelDiameterEntities = _wheelDiameterRepository.getAll();
            WheelDiameterEntity emptySelect = new WheelDiameterEntity(0, "");
            wheelDiameterEntities.add(0, emptySelect);
            response.setObject(wheelDiameterEntities);
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


    public ServiceObjectResponse<WheelDiameterEntity> getById(int id)
    {
        ServiceObjectResponse<WheelDiameterEntity> response = new ServiceObjectResponse<>();

        try
        {
            WheelDiameterEntity wheelDiameterEntity = _wheelDiameterRepository.getById(id);

            response.setObject(wheelDiameterEntity);
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

    public ServiceObjectResponse<WheelDiameterEntity> create(WheelDiameterEntity wheelDiameterEntity)
    {
        ServiceObjectResponse<WheelDiameterEntity> response = new ServiceObjectResponse<>();

        try
        {
            WheelDiameterEntity data = _wheelDiameterRepository.WheelDiameterCreate(wheelDiameterEntity);

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

    public ServiceObjectResponse<WheelDiameterEntity> update(WheelDiameterEntity wheelDiameterEntity)
    {
        ServiceObjectResponse<WheelDiameterEntity> response = new ServiceObjectResponse<>();

        try
        {
            WheelDiameterEntity data = _wheelDiameterRepository.update(wheelDiameterEntity);

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
            boolean success = _wheelDiameterRepository.delete(id);

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

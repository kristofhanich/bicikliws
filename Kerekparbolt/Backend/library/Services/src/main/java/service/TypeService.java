package service;

import common.ServiceObjectResponse;
import entity.TypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ITypeRepository;
import services.ITypeService;

import java.util.List;

@Service
public class TypeService implements ITypeService {

    @Autowired
    ITypeRepository _typeRepository;

    public ServiceObjectResponse<List<TypeEntity>> getAll()
    {
        ServiceObjectResponse<List<TypeEntity>> response = new ServiceObjectResponse<>();

        try
        {
            List<TypeEntity> typeEntities = _typeRepository.getAll();
            TypeEntity emptySelect = new TypeEntity(0, "");
            typeEntities.add(0, emptySelect);
            response.setObject(typeEntities);
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


    public ServiceObjectResponse<TypeEntity> getById(int id)
    {
        ServiceObjectResponse<TypeEntity> response = new ServiceObjectResponse<>();

        try
        {
            TypeEntity typeEntity = _typeRepository.getById(id);

            response.setObject(typeEntity);
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

    public ServiceObjectResponse<TypeEntity> create(TypeEntity typeEntity)
    {
        ServiceObjectResponse<TypeEntity> response = new ServiceObjectResponse<>();

        try
        {
            TypeEntity data = _typeRepository.TypeCreate(typeEntity);

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

    public ServiceObjectResponse<TypeEntity> update(TypeEntity typeEntity)
    {
        ServiceObjectResponse<TypeEntity> response = new ServiceObjectResponse<>();

        try
        {
            TypeEntity data = _typeRepository.update(typeEntity);

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
            boolean success = _typeRepository.delete(id);

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

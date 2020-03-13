package service;

import common.ServiceObjectResponse;
import entity.UserEntity;
import enums.Role;
import repositories.IUserRepository;
import request.RegisterRequest;
import response.UserResponse;
import services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;


@Service
public class UserService implements IUserService
{
    @Autowired
    IUserRepository _userRepository;

    @Override
    public ServiceObjectResponse<UserEntity> create(RegisterRequest data) throws Exception
    {
        ServiceObjectResponse<UserEntity> response = new ServiceObjectResponse<>();

        try
        {
            UserEntity user = _userRepository.findByEmail(data.Email);

            if(user != null)
            {
                throw new Exception("Email already taken!");
            }

            user = new UserEntity(data);
            user.Jelszo = passwordEncoder(data.Jelszo);
            user.UniqID = UUID.randomUUID().toString();
            user.Role = Role.ROLE_CLIENT.toString();

             user = _userRepository.create(user);

            response.setObject(user);
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

    @Override
    public ServiceObjectResponse<UserEntity> findByUniqID(String uniqID) throws Exception
    {
        ServiceObjectResponse<UserEntity> response = new ServiceObjectResponse<>();

        try
        {
            UserEntity user = _userRepository.findByUniqID(uniqID);

            response.setObject(user);
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

    @Override
    public ServiceObjectResponse<UserEntity> findByCredentials(String email, String jelszo) throws Exception
    {
        ServiceObjectResponse<UserEntity> response = new ServiceObjectResponse<>();

        try
        {
            String hashedPassword = passwordEncoder(jelszo);
            UserEntity user = _userRepository.findByCredentials(email, hashedPassword);

            if(user == null)
            {
                throw new Exception("Wrong credentials!");
            }

            response.setObject(user);
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

    @Override
    public ServiceObjectResponse<UserEntity> findByEmail(String email) throws Exception
    {
        ServiceObjectResponse<UserEntity> response = new ServiceObjectResponse<>();

        try
        {
            UserEntity user = _userRepository.findByEmail(email);

            response.setObject(user);
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

    @Override
    public ServiceObjectResponse<UserResponse> whoAmI(String uniqID) throws Exception
    {
        ServiceObjectResponse<UserResponse> response = new ServiceObjectResponse<>();

        try
        {
            UserEntity user = _userRepository.findByUniqID(uniqID);

            if(user == null)
            {
                throw new Exception("Can't find user!");
            }

            UserResponse whoAmI = new UserResponse(user);

            response.setObject(whoAmI);
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

    private String passwordEncoder(String password) throws Exception
    {
        String secret = Base64.getEncoder().encodeToString(password.getBytes());

        return  secret;
    }

}

package repositories;

import entity.UserEntity;

public interface IUserRepository
{
    UserEntity create(UserEntity user) throws Exception;
    UserEntity findByUniqID(String uniqID) throws Exception;
    UserEntity findByCredentials(String email, String jelszo) throws Exception;
    UserEntity findByEmail(String email) throws Exception;
}

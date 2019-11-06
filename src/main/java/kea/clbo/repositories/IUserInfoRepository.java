package kea.clbo.repositories;

import kea.clbo.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInfoRepository extends CrudRepository<UserInfo, Integer> {

}

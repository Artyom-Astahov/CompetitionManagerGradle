package by.artem.artem.database.repository;

import by.artem.entity.UserInfo;

import javax.persistence.EntityManager;

public class UserInfoRepository extends BaseRepository<Integer, UserInfo>{
    public UserInfoRepository(EntityManager entityManager) {
        super(UserInfo.class, entityManager);
    }
}

package aruaru.user.repository;

import org.springframework.stereotype.Repository;

import aruaru.user.User;
import aruaru.user.infra.db.dao.UserMDao;
import aruaru.user.infra.db.entity.UserDto;

@Repository
public class UserRepository {

    private final UserMDao userMDao;

    public UserRepository(UserMDao userMDao) {
        this.userMDao = userMDao;
    }

    public User selectById(aruaru.user.User.Id userId){
        UserDto userDto= userMDao.selectUserDtoById(userId.id());
        User user = User.create(userDto.getUserId(), userDto.getUserName(), userDto.getUserPassword(), userDto.getUserEmail(), userDto.getUserRoleName());
        return user;
    };
}

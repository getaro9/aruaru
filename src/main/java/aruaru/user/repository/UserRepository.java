package aruaru.user.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aruaru.user.User;
import aruaru.user.infra.db.dao.UserMDao;
import aruaru.user.infra.db.entity.UserDto;

@Transactional
@Repository
public class UserRepository {

    private final UserMDao userMDao;

    public UserRepository(UserMDao userMDao) {
        this.userMDao = userMDao;
    }

    public User selectById(aruaru.user.User.Id userId){
        UserDto userDto= userMDao.selectUserDtoById(userId.id());
        User user = new User(userDto.getUserId(), userDto.getUserName(), userDto.getUserPassword(), userDto.getUserEmail(), userDto.getUserRoleName());
        return user;
    };

    public User insert(User user){

        UserDto userDto = new UserDto();
        userDto.setUserName(user.name().name());
        userDto.setUserPassword(user.password().password());
        userDto.setUserEmail(user.email().email());
        userDto.setUserRoleName(user.role().role());

        int result =userMDao.insertUserDto(userDto);
        int userId = userMDao.currentId();

        User reUser= user.withId(new User.Id(userId));

        return reUser;
    };

    public User update(User user) {

      UserDto userDto = new UserDto();
      userDto.setUserId(user.id().id());
      userDto.setUserName(user.name().name());
      userDto.setUserPassword(user.password().password());
      userDto.setUserEmail(user.email().email());
      userDto.setUserRoleName(user.role().role());

      int result = userMDao.updateUserDto(userDto);
      int userId = userMDao.currentId();

      User reUser = user.withId(new User.Id(userId));

      return reUser;
    };
}

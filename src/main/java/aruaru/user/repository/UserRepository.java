package aruaru.user.repository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aruaru.user.User;
import aruaru.user.infra.db.dao.UserMDao;
import aruaru.user.infra.db.entity.UserDto;
import aruaru.user.infra.db.entity.UserM;

@Transactional
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

    public User save(User user){

        UserDto userDto = new UserDto();
        userDto.setUserId(user.id().id());
        userDto.setUserName(user.name().name());
        userDto.setUserPassword(user.password().password());
        userDto.setUserEmail(user.email().email());
        userDto.setUserRoleName(user.role().role());

        int result =userMDao.insertUserDto(userDto);

        User reUser= user.withId(new User.Id(userDto.getUserId()));

        UserM userM = new UserM();
        userM.setUserId(user.id().id());
        userM.setUserName(user.name().name());
        userM.setUserPassword(user.password().password());
        userM.setUserEmail(user.email().email());
        userM.setUserRoleId((short) 10);
        userM.setCreatedAt(LocalDateTime.now());

        int result2 = userMDao.insert(userM);


//        User user = User.create(userDto.getUserId(), userDto.getUserName(), userDto.getUserPassword(), userDto.getUserEmail());
        return reUser;
    };
}

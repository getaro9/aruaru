package aruaru.user.infra.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import aruaru.user.infra.db.entity.UserDto;
import aruaru.user.infra.db.entity.UserM;

/**
 */
@ConfigAutowireable
@Dao
public interface UserMDao {

    @Select
    UserDto selectUserDtoById(Integer userId);


    @Insert(sqlFile = true)
    int insertUserDto(UserDto userDto);

    /**
     * @param userId
     * @return the UserM entity
     */
    @Select
    UserM selectById(Integer userId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(UserM entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(UserM entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(UserM entity);
}

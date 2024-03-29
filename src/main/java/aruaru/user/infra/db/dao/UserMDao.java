package aruaru.user.infra.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
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


    @Sql("select currval(pg_catalog.pg_get_serial_sequence('user_m', 'user_id'))")
    @Select
    int currentId();

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

    @Update(sqlFile = true)
    int updateUserDto(UserDto userDto);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(UserM entity);
}

package life.lidy.community.community.mapper;

import life.lidy.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);//参数不是类时要加@Param("token")
    @Select("select * from user where account_id = #{account_id}")
    User findByAccountId(@Param("account_id")String creator);
    @Update("update user set token = #{token} where account_id = #{account_id} ")
    void updateTokenByAccountId(@Param("account_id") String accountId,@Param("token")String token);
}

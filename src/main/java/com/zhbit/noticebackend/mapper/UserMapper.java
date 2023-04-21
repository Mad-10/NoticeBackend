package com.zhbit.noticebackend.mapper;

import com.zhbit.noticebackend.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int deleteById(@Param("id") Long id);

    @Update("UPDATE user SET username=#{username}, password=#{password} WHERE id=#{id}")
    int update(User user);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getById(@Param("id") Long id);

    @Select("SELECT * FROM user WHERE username=#{username}")
    User getByUsername(@Param("username") String username);

    @Select("SELECT * FROM user")
    @Results(id = "userResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    List<User> listAll();

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetByConditionSql")
    List<User> getByCondition(@Param("user") User user, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    class UserSqlBuilder {
        public static String buildGetByConditionSql(@Param("user") User user, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
            SQL sql = new SQL()
                    .SELECT("*")
                    .FROM("user");
            if (user != null) {
                if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                    sql.WHERE("username LIKE CONCAT('%', #{user.username}, '%')");
                }
                if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                    sql.WHERE("password LIKE CONCAT('%', #{user.password}, '%')");
                }
            }
            if (startIndex != null && pageSize != null) {
                sql.LIMIT("#{startIndex}, #{pageSize}");
            }
            return sql.toString();
        }
    }
}

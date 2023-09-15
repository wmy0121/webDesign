package webDesign.dao;

import org.apache.ibatis.annotations.*;
import webDesign.domain.Book;
import webDesign.domain.User;

import java.util.List;

public interface UserDao {

    @Insert("insert into user (username,password) values(#{username},#{password})")
    public void save(User user);

    @Select("select * from user where username = #{username} ")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%', #{username}, '%')")
    public List<User> getUserAll(String username);

    @Select("select * from user where id=#{userId}")
    public List<User> getUserAllById(Integer userId);


    @Update("update user set username = #{username}, password = #{password}  where id = #{id}")
    public void update(User user);

    @Delete("delete from user where id = #{id}")
    public void delete(Integer id);

    @Select("select * from user where id = #{id}")
    public User getById(Integer id);
}

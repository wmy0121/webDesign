package webDesign.dao;

import org.apache.ibatis.annotations.*;
import webDesign.domain.Book;


import java.util.List;

public interface BookDao {
    @Insert("insert into tbl_book (type,name,description,userId) values(#{type},#{name},#{description},#{userId})")
    public void save(Book book);
    @Update("update tbl_book set type = #{type}, name = #{name}, description = #{description} where id = #{id}")
    public void update(Book book);
    @Delete("delete from tbl_book where id = #{id}")
    public void delete(Integer id);
    @Select("select * from tbl_book where id = #{id}")
    public Book getById(Integer id);
    @Select("select * from tbl_book where userId=#{userId} and name LIKE CONCAT('%', #{bookName}, '%')")
    public List<Book> getAll(@Param("userId") Integer userId, @Param("bookName") String bookName);


    @Select("SELECT * FROM tbl_book WHERE name LIKE CONCAT('%', #{bookName}, '%')")
    public List<Book> getBookAll(String bookName);


}

package com.mapper;

import com.entity.Book;
import com.entity.Borrow;
import com.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Insert("insert into student(name,sex,grade) values (#{name},#{sex},#{grade})")
    int addStudent(Student student);
    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price}) ")
    int addBook(Book book);
    @Insert("insert into borrow(bid,sid) values(#{bid},#{sid})")
    int addBorrow(@Param("bid") int bid,@Param("sid") int sid);

    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "sid",property = "student",one = @One(select = "getStudent")),
            @Result(column = "bid",property = "book",one = @One(select = "getBook"))
    })
    @Select("select * from borrow")
    List<Borrow> listBorrows();

    @Select("select * from student")
    List<Student> listStudents();

    @Select("select * from student where sid = #{sid}")
    Student getStudent(int sid);
    @Select("select * from book where bid = #{bid}")
    Book getBook(int bid);


}

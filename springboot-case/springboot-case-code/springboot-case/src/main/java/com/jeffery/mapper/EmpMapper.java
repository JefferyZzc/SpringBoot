package com.jeffery.mapper;

import com.jeffery.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

   /* @Select("select count(*) from emp")
    public Long count();

    @Select("select * from emp limit #{start},#{PageSize} ")
    public List<Emp> page(Integer start, Integer PageSize);*/


    List<Emp> conditionalPageSelect(String name, Short gender, LocalDate begin, LocalDate end);

   // @Select("select * from emp")
    List<Emp> pageSelect(Integer start, Integer pageSize,List<String> empName);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time    )" +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernamdAndPassword(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}

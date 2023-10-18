package com.jeffery.service.impl;


import com.jeffery.mapper.EmpMapper;
import com.jeffery.pojo.Emp;
import com.jeffery.pojo.PageBean;
import com.jeffery.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        Long count = empMapper.count();
        List<Emp> empList = empMapper.page((page-1)*pageSize,pageSize);
        return new PageBean(count,empList);
    }
*/
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {


        List<String> empName = new ArrayList<>();
        List<Emp> empList = empMapper.conditionalPageSelect(name, gender, begin, end);
        for(Emp emp : empList){
            for (int i = 0; i < empList.stream().count(); i++) {
                empName.add(emp.getName());
            }
        }
        List<Emp> empPageList = empMapper.pageSelect((page-1)*pageSize,pageSize,empName);
        return new PageBean(empList.stream().count(),empPageList);
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernamdAndPassword(emp);
    }
}

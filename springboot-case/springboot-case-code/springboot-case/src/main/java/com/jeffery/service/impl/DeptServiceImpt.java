package com.jeffery.service.impl;

import com.jeffery.mapper.DeptMapper;
import com.jeffery.mapper.EmpMapper;
import com.jeffery.pojo.Dept;
import com.jeffery.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpt implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
            deptMapper.deleteById(id);
            int i = 1/0;
            empMapper.deleteByDeptId(id);//根据部门id删除员工
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }


}

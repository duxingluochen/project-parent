package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.mapper.EmployeeMapper;
import com.mapscience.modular.system.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-07
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}

package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.AccountType;
import com.mapscience.modular.system.mapper.AccountTypeMapper;
import com.mapscience.modular.system.service.IAccountTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 户口类别表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-07
 */
@Service
public class AccountTypeServiceImpl extends ServiceImpl<AccountTypeMapper, AccountType> implements IAccountTypeService {

}

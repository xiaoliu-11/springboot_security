package com.znzz.springboot_security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.znzz.springboot_security.entity.Users;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<Users> {

}

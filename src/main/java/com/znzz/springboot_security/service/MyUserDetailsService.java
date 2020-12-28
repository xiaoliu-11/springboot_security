package com.znzz.springboot_security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.znzz.springboot_security.mapper.UserMapper;
import com.znzz.springboot_security.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
     private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //调用mapper里面的方法查询。
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        Users users = userMapper.selectOne(wrapper);

        //判断。
        if(users == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        System.out.println(users);

        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
           //将得到的用户名和密码返回。
        return new User(users.getUsername(),
              new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}

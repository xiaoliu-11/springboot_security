package com.example.partone.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        BCryptPasswordEncoder  pass = new BCryptPasswordEncoder();
        String pasword = pass.encode("123456");//用于密码的加密

                   auth.inMemoryAuthentication().withUser("lsg").password(pasword).roles("admin");
    }

    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

}

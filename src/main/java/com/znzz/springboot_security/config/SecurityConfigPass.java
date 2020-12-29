package com.znzz.springboot_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigPass extends WebSecurityConfigurerAdapter {
    @Autowired
      private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
       // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;

    }
       @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
           auth.userDetailsService(userDetailsService).passwordEncoder(password());
       }

       @Bean
    PasswordEncoder password()
       {
           return  new BCryptPasswordEncoder();
       }


   //自定义登录页面
    @Override
    protected void configure(HttpSecurity http) throws Exception {
           //配置没有权限访问跳转的自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");
//        //用户注销（退出）
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/test/hello").permitAll();

      http.formLogin()            //自定义的登录页面。
                    .loginPage("/login.html")  //登录页面设置
                    .loginProcessingUrl("/user/login")    //登录访问路径
                    .defaultSuccessUrl("/success.html").permitAll() //登录成功之后的跳转路径
                    .and().authorizeRequests()
                    .antMatchers("/","/test/hello","user/login").permitAll()   //设置哪些路径不需要权限认
                     //表示该访问地址，只有去权限为user1的用户才能访问该路径。



                    .antMatchers("/test/index").hasAnyRole("user1")
                    .anyRequest().authenticated()
                    .and().rememberMe().tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(60)                    //设置有效时常，单位：秒
                    .userDetailsService(userDetailsService)      //从数据库中获取数据
                    .and().csrf().disable();  //关闭csrf防护
    }
}

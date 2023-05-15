package com.hn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.logout()
                    .logoutUrl("/logout")//设置注销URL路径
                    .logoutSuccessUrl("/sms/system")//设置注销成功后跳转的URL路径
                    .invalidateHttpSession(true)//设置注销成功后,是否销毁session
                    .deleteCookies("JSESSIONID");//设置注销成功后,删除指定的cookie
    }
}

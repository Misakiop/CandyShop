package cn.lmu.candy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class MySecurityConfig {
    @Autowired
    private  MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private  MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private  JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    //核心配置
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(it -> it.disable()) //关闭csrf
                .authorizeHttpRequests(it ->it.requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") //管理员角色才能访问管理模块
                        .anyRequest().authenticated() //任何路径都要进行拦截
                ).exceptionHandling(it->it.accessDeniedHandler(myAccessDeniedHandler)
                        .authenticationEntryPoint(myAuthenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    //放行静态资源目录
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**", "/image/**", "/js/**");
    }
}

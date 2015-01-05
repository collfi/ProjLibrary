package cz.fi.muni.pa165.library.web;

/**
* Created by sergii on 03.01.15.
*/


import cz.fi.muni.pa165.library.api.service.MemberService;
import cz.fi.muni.pa165.service.MemberServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    TODO
    public MemberService memberService = new MemberServiceImpl();

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(memberService);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                        // Spring Security should completely ignore URLs ending with .html
//                .antMatchers("/*.html");
//
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").hasRole("ADMIN")
//                .and()
//                .formLogin();

//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/**").access("hasRole('ROLE_ADMIN')")
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout")
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//                .and()
//                .csrf();

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login");


//        http
//                .authorizeRequests()
//                .antMatchers("/j_spring_security_check").permitAll()
////                .antMatchers("/static/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginPage("/login")
//                .permitAll()
////                .defaultSuccessUrl("/")
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");
    }

}

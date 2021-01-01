package example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static example.hello.HelloSecurityRoles.ADMIN;
import static example.hello.HelloSecurityRoles.USER;

@Configuration
@EnableWebSecurity
public class HelloSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/login", "/webjars/**", "/css/**", "/js/**").permitAll()
                    .antMatchers("/admin").hasRole(ADMIN)
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("123456").roles(ADMIN)
            .and().withUser("user").password( "password").roles(USER);
    }
}

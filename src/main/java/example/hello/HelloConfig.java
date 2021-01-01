package example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
@EnableWebSecurity
public class HelloConfig extends WebSecurityConfigurerAdapter {
    static class Roles {
        static final String ADMIN = "ADMIN";
        static final String USER = "USER";
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/login", "/webjars/**", "/css/**", "/js/**").permitAll()
                    .antMatchers("/admin").hasRole(Roles.ADMIN)
                    .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                    .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.NOT_FOUND),
                            new NegatedRequestMatcher(new AntPathRequestMatcher("/login**")) )
                .and()
                .formLogin()
                ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("123456").roles(Roles.ADMIN)
            .and().withUser("user").password( "password").roles(Roles.USER);
    }
    @Bean
    public HelloMVCController helloMVCController() {
        return new HelloMVCController();
    }

}

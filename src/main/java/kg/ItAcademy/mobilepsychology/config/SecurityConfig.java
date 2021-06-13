package kg.ItAcademy.mobilepsychology.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/posts/**").hasAnyRole("PSYCHOLOGIST", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/posts/**").hasAnyRole("PSYCHOLOGIST", "ADMIN")

                .antMatchers(HttpMethod.GET, "/api/comment/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/comment/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/comment/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/like/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/like/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/like/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/follower/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/follower/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/follower/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/feedback").permitAll()
                .antMatchers(HttpMethod.POST, "/api/feedback").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/feedback").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/api/timetable/**").hasAnyRole("PSYCHOLOGIST", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/timetable/**").hasAnyRole("PSYCHOLOGIST", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/timetable/**").hasAnyRole("PSYCHOLOGIST", "ADMIN")

                .antMatchers(HttpMethod.GET, "/api/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/roles").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and().logout().and().formLogin() ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status from users where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.role_name from user_role ur join users u on ur.user_id = u.id where u.username=? and status=1");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

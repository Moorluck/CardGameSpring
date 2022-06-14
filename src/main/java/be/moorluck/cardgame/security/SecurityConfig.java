package be.moorluck.cardgame.security;

import be.moorluck.cardgame.filter.CustomAuthenticationFilter;
import be.moorluck.cardgame.filter.CustomAuthorizationFilter;
import be.moorluck.cardgame.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(cryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(userService), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().antMatchers("/user/login").permitAll()
            .antMatchers("/user/refresh").permitAll()
            .antMatchers(HttpMethod.GET, "/card/**").permitAll()
            .antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ADMIN")
            .antMatchers(HttpMethod.GET, "/role/**").hasAnyAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/role").hasAnyAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/card").hasAnyAuthority("ADMIN")
            .antMatchers(HttpMethod.GET, "/deck/**").authenticated()
            .antMatchers(HttpMethod.POST, "/user").authenticated()
            .anyRequest().permitAll();


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

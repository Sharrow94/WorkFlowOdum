package pl.odum.workflowodum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/meeting/details/{meetingId}")
                    .access("@currentUser.isCurrentUsersMeeting(#meetingId, authentication) or hasRole('ADMIN')")
                .antMatchers("/app/**").hasAnyRole( "USER","ADMIN")
                .antMatchers("/admin**").hasAnyRole("ADMIN")
                .antMatchers("/super**").hasAnyRole("ADMIN")
                .antMatchers("/app/meeting/{meetingId}/send-attachment/{docUUID}").hasAnyRole("ADMIN")

                .and().formLogin().loginPage("/login").defaultSuccessUrl("/app/home")
                .and().logout().logoutSuccessUrl("/login")
                .permitAll();
    }
}

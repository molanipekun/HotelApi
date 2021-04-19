package registration.login.security.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Configuration
    @Order(1)
    public static class ApplicationHttpUserConfigurationAdapter extends WebSecurityConfigurerAdapter{

        @Bean
        public static BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        private JwtAuthenticationEntryPoint unauthorizedHandler;

        @Autowired
        @Qualifier("jwtservice")
        private UserDetailsService userDetailsService;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception{
            return super.authenticationManagerBean();
        }


        @Autowired
        public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder
                    .userDetailsService(this.userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }

        @Bean
        public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
            return new JwtAuthenticationTokenFilter();
        }


        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            //httpSecurity.addFilterBefore(new CorsFilter(), BasicAuthenticationFilter.class);

            // Custom JWT based security filter
            httpSecurity
                    .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

            httpSecurity
                    .csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                    // don't create session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/api/v1/**","/api/hotel/reservation/createuser").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/**","/api/hotel/reservation/createuser").permitAll()
                    .antMatchers(HttpMethod.GET, "/v2/*","/swagger-ui.html","/swagger-resources/**","/configuration/ui","/webjars/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/api/**","/hotel/reservation/api/**").permitAll()
                    .anyRequest().authenticated();


        }
    }
}

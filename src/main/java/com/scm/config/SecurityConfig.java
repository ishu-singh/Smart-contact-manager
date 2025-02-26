package com.scm.config;
//for security configuration fro user for login

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // user create and login using java code in memory service

    // @Bean
    // public UserDetailsService userDetailsService(){

    // UserDetails user1=User
    // .withDefaultPasswordEncoder()
    // .username("admin1")
    // .password("admin1")
    // .roles("ADMIN","USER")
    // .build();

    // UserDetails user2=User
    // .withUsername("admin2")
    // .password("{noop}admin2")
    // .roles("ADMIN","USER")
    // .build();
    // var inMemoryUserDetailsManager= new InMemoryUserDetailsManager(user1,user2);
    // return inMemoryUserDetailsManager;

    // Dao Authentication provider for user login including our database

    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    // configuration of authentication provider

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail service object
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        // password encode object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecuirty) throws Exception {

        // configuration of http security
        // urls have been configured so that urls are accessible by all and some urls
        // are accesible by users only
        httpSecuirty.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        // form default login page
        // if we need to change anyhting regarding form we can come here

        httpSecuirty.formLogin(formLogin -> {
            // formLogin.loginPage(Customizer.withDefaults());

            // to add our own login page instead of the default login page provided by
            // springBoot
            formLogin.loginPage("/login");
            // form will be submitted to authenticate page
            formLogin.loginProcessingUrl("/authenticate");

            // after successfull login, the user will land up to this page
            formLogin.defaultSuccessUrl("/user/dashboard");
            // after failure of login, the user will land up to this page
            // formLogin.failureUrl("/login?error=true");
            //field names
            formLogin.usernameParameter("email");
            
            formLogin.passwordParameter("password");
            //handler for handling failure
            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // //handler for handling success
            // formLogin.successHandler((AuthenticationSuccessHandler) new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });


        });
        httpSecuirty.csrf(AbstractHttpConfigurer::disable);
        httpSecuirty.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });
        //oauth configuration
        httpSecuirty.oauth2Login(oauth->{
            oauth.loginPage("/login");
        });

        return httpSecuirty.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}

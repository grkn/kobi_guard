package com.kobiguard.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/kobiguard/**").fullyAuthenticated().and()
                .authenticationProvider(customAuthenticationProvider).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("MaYzkSjmkzPC57L");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               /* UserEntity userEntity = userService.getUserByUsernameOrEmail(username, username);
                if (userEntity == null) {
                    throw new UsernameNotFoundException(String.format("Username or Email {0} not found", username));
                }

                Set<GrantedAuthority> grantedAuths = userEntity.getUserAuthorization().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toSet());
                */
                return new User("userEntity.getEmailAddress()", " userEntity.getAccountPhrase()", Collections.singletonList(new SimpleGrantedAuthority("")));
            }
        };
    }

    @Component
    public static class CustomAuthenticationProvider implements AuthenticationProvider {
        private final PasswordEncoder passwordEncoder;

        public CustomAuthenticationProvider(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public Authentication authenticate(Authentication authentication) {
            String username = authentication.getName();
            String password = (String) authentication.getCredentials();
            /*
            UserEntity userEntity = this.userService.getUserByUsernameOrEmail(username, username);

            if (userEntity == null) {
                throw new BadCredentialsException("Username or Password not found.");
            }

            if (!passwordEncoder.matches(password, userEntity.getAccountPhrase())) {
                throw new BadCredentialsException("Username or Password not found.");
            }

            Set<GrantedAuthority> grantedAuths = userEntity.getUserAuthorization().stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toSet());
            */
            return new UsernamePasswordAuthenticationToken(username, password, Collections.singletonList(new SimpleGrantedAuthority("")));
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return true;
        }
    }

    @Component
    public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

        @Override
        public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
            configurer.jdbc(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore()).reuseRefreshTokens(false).accessTokenConverter(accessTokenConverter())
                    .authenticationManager(authenticationManager()).userDetailsService(userDetailsService());

        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.tokenKeyAccess("hasAuthority('ROLE_ADMIN')")
                    .checkTokenAccess("hasAuthority('ROLE_ADMIN')");
        }
    }

    @Component
    public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Autowired
        private ResourceServerTokenServices tokenServices;

        @Autowired
        private JwtAccessTokenConverter accessTokenConverter;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenServices(tokenServices);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/actuator/**", "/api-docs/**", "/oauth/*").permitAll()
                    .antMatchers("/kobiguard/**").authenticated();
        }

    }
}

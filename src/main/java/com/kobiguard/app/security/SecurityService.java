package com.kobiguard.app.security;

import com.kobiguard.app.entity.User;
import com.kobiguard.app.resources.UserResource;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Component
public class SecurityService {

    private final ConversionService conversionService;
    private final DefaultTokenServices defaultTokenServices;
    private final DataSource dataSource;

    public SecurityService(ConversionService conversionService, DefaultTokenServices defaultTokenServices, DataSource dataSource) {
        this.conversionService = conversionService;
        this.defaultTokenServices = defaultTokenServices;
        this.dataSource = dataSource;
    }

    public void createUserAccessToken(User createdUser) {

        UserResource userResource = conversionService.convert(createdUser, UserResource.class);
        String simpleAuthorityName = "ROLE_" + userResource.getUserType().name();

        OAuth2Request oAuth2Request = new OAuth2Request(new HashMap<>(),
                userResource.getNickName(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority(simpleAuthorityName)),
                true, Collections.singleton(userResource.getId())
                , null, null, null, Collections.singletonMap("user_information", userResource));


        org.springframework.security.core.userdetails.User authenticatedUser =
                new org.springframework.security.core.userdetails.User(userResource.getNickName(),
                        userResource.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority(simpleAuthorityName)));


        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request,
                new CustomAuthentication(userResource, simpleAuthorityName, authenticatedUser));

        defaultTokenServices.createAccessToken(oAuth2Authentication);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update("INSERT INTO OAUTH_CLIENT_DETAILS  "
                + "(client_id, client_secret, scope, authorized_grant_types,  "
                + "authorities, access_token_validity, refresh_token_validity) VALUES "
                + "('" + userResource.getNickName() + "','" + userResource.getPassword()
                + "', 'read,write', 'password,refresh_token,client_credentials,authorization_code'," +
                " 'ROLE_USER," + simpleAuthorityName + "', 900, 2592000)");
    }

    public static class CustomAuthentication implements Authentication {

        private final UserResource createdUser;
        private final org.springframework.security.core.userdetails.User authenticatedUser;
        private final String simpleAuthorityName;

        public CustomAuthentication(UserResource createdUser, String simpleAuthorityName,
                                    org.springframework.security.core.userdetails.User authenticatedUser) {
            this.createdUser = createdUser;
            this.simpleAuthorityName = simpleAuthorityName;
            this.authenticatedUser = authenticatedUser;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority(simpleAuthorityName));
        }

        @Override
        public Object getCredentials() {
            return createdUser.getPassword();
        }

        @Override
        public Object getDetails() {
            return authenticatedUser;
        }

        @Override
        public Object getPrincipal() {
            return createdUser.getNickName();
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean b) throws IllegalArgumentException {
        }

        @Override
        public String getName() {
            return createdUser.getName();
        }
    }
}

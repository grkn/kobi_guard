package com.kobiguard.app.init;

import com.kobiguard.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * TO BE DELETED
 *
 * @author grkn
 */
@Component
public class InitializeRootUser {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    // TO BE DELETED
    @PostConstruct
    public void setUp() {

        String encodedPassword = passwordEncoder.encode("grkn");

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            jdbcTemplate.update("INSERT INTO OAUTH_CLIENT_DETAILS  "
                    + "(client_id, client_secret, scope, authorized_grant_types,  "
                    + "authorities, access_token_validity, refresh_token_validity) VALUES "
                    + "('grkn','" + encodedPassword
                    + "', 'read,write', 'password,refresh_token,client_credentials,authorization_code', 'ROLE_ADMIN,ROLE_USER', 900, 2592000)");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
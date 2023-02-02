package springboot.springsecurity_OAuth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import springboot.springsecurity_OAuth2.common.authority.CustomAuthorityMapper;

@Configuration
public class OAuth2AppConfig {

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper(){
        return new CustomAuthorityMapper();
    }

}

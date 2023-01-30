package springboot.springsecurity_OAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import springboot.springsecurity_OAuth2.service.CustomOAuth2UserService;
import springboot.springsecurity_OAuth2.service.CustomOidcUserService;

@Configuration
public class OAuth2ClientConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomOidcUserService customOidcUserService;


//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
//    }
//
//    private ClientRegistration keycloakClientRegistration() {
//        return ClientRegistrations.fromIssuerLocation("http://localhost:8080/realms/oauth2")
//                .registrationId("keycloak")
//                .clientId("oauth2-client-app")
//                .clientSecret("gcCVBjhdUjfSN7ZNfG8S4eQNravy6bWr")
//                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
//                .issuerUri("")
//                .build();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/static/**"));
    }


    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                .antMatchers("/api/user")
                .access("hasAnyRole('SCOPE_profile','SCOPE_email')")
//                .access("hasAuthority('SCOPE_profile')")
                .antMatchers("/api/oidc")
                .access("hasRole('SCOPE_openid')")
                //.access("hasAuthority('SCOPE_openid')")
                .antMatchers("/")
                .permitAll()
                .anyRequest().authenticated());
        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)
                        .oidcUserService(customOidcUserService)));
        http.logout().logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }
}

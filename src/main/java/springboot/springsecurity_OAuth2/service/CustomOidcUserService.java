package springboot.springsecurity_OAuth2.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import springboot.springsecurity_OAuth2.common.converters.ProviderUserRequest;
import springboot.springsecurity_OAuth2.model.users.PrincipalUser;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        // Open ID Connect 인 경우 User name Attribute Key 가 sub 이기 때문에 재정의함
        ClientRegistration clientRegistration = ClientRegistration
                                                .withClientRegistration(userRequest.getClientRegistration())
                                                .userNameAttributeName("sub")
                                                .build();
        OidcUserRequest oidcUserRequest =
                new OidcUserRequest(clientRegistration, userRequest.getAccessToken(),
                        userRequest.getIdToken(), userRequest.getAdditionalParameters());

        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
        OidcUser oidcUser = oidcUserService.loadUser(oidcUserRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oidcUser);
        ProviderUser providerUser = providerUser(providerUserRequest);

        selfCertificate(providerUser);

        super.register(providerUser, oidcUserRequest);

        return new PrincipalUser(providerUser);
    }
}

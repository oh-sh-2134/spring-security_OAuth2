package springboot.springsecurity_OAuth2.converters;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import springboot.springsecurity_OAuth2.model.user.User;

public record ProviderUserRequest (ClientRegistration clientRegistration, OAuth2User oAuth2User, User user){
    public ProviderUserRequest(ClientRegistration clientRegistration, OAuth2User oAuth2User){
        this(clientRegistration,oAuth2User,null);
    }

    public ProviderUserRequest(User user){
        this(null,null,user);
    }
}

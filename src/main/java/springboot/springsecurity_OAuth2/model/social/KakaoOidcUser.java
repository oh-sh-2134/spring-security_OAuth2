package springboot.springsecurity_OAuth2.model.social;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import springboot.springsecurity_OAuth2.model.Attributes;
import springboot.springsecurity_OAuth2.model.OAuth2ProviderUser;

public class KakaoOidcUser extends OAuth2ProviderUser {

    public KakaoOidcUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getMainAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return (String)getAttributes().get("id");
    }

    @Override
    public String getUsername() {
        return (String)getAttributes().get("nickname");
    }

    @Override
    public String getPicture() {
        return (String)getAttributes().get("profile_image_url");
    }
}
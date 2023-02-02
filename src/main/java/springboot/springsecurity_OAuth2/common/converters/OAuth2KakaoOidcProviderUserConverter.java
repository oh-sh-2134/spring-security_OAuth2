package springboot.springsecurity_OAuth2.common.converters;


import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import springboot.springsecurity_OAuth2.common.enums.OAuth2Config;
import springboot.springsecurity_OAuth2.common.util.OAuth2Utils;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.social.KakaoOidcUser;

public final class OAuth2KakaoOidcProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        if (!(providerUserRequest.oAuth2User() instanceof OidcUser)) {
            return null;
        }

        return new KakaoOidcUser(OAuth2Utils.getMainAttributes(
                providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}

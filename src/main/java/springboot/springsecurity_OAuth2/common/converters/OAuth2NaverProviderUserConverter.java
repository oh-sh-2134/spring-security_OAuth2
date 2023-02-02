package springboot.springsecurity_OAuth2.common.converters;


import springboot.springsecurity_OAuth2.common.enums.OAuth2Config;
import springboot.springsecurity_OAuth2.common.util.OAuth2Utils;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.social.NaverUser;

public final class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(
                providerUserRequest.oAuth2User(), "response"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}

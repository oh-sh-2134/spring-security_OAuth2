package springboot.springsecurity_OAuth2.common.converters;


import springboot.springsecurity_OAuth2.common.enums.OAuth2Config;
import springboot.springsecurity_OAuth2.common.util.OAuth2Utils;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.social.GoogleUser;

public final class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(
                providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}

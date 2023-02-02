package springboot.springsecurity_OAuth2.common.converters;


import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.User;
import springboot.springsecurity_OAuth2.model.users.form.FormUser;

public final class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if(providerUserRequest.user() == null){
            return null;
        }

        User user = providerUserRequest.user();
        return FormUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .email(user.getEmail())
                .provider("none")
                .build();
    }
}

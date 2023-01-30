package springboot.springsecurity_OAuth2.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import springboot.springsecurity_OAuth2.model.*;
import springboot.springsecurity_OAuth2.repository.UserRepository;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        User user = userRepository.findByUsername(providerUser.getUsername());

        if (user == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            userService.register(registrationId, providerUser);
        }else{
            System.out.println("user = " + user);
        }
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User){
        String registrationId = clientRegistration.getRegistrationId();
//        if (registrationId.equals("keycloak")) {
//            return new KeycloakUser(oAuth2User, clientRegistration);
//        }
//        else
        if(registrationId.equals("google")){
            return new GoogleUser(oAuth2User, clientRegistration);

        }else if(registrationId.equals("naver")){
            return new NaverUser(oAuth2User, clientRegistration);
        }
        return null;
    }

}

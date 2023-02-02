package springboot.springsecurity_OAuth2.service;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import springboot.springsecurity_OAuth2.certification.SelfCertification;
import springboot.springsecurity_OAuth2.common.converters.ProviderUserConverter;
import springboot.springsecurity_OAuth2.common.converters.ProviderUserRequest;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.User;
import springboot.springsecurity_OAuth2.repository.UserRepository;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SelfCertification certification;
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    public void selfCertificate(ProviderUser providerUser){
        certification.checkCertification(providerUser);
    }
    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){

        User user = userRepository.findByUsername(providerUser.getUsername());

        if(user == null){
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(),providerUser);
        }else{
            System.out.println("userRequest = " + userRequest);
        }
    }
    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.convert(providerUserRequest);
    }
}

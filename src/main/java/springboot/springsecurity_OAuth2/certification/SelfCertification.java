package springboot.springsecurity_OAuth2.certification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.User;
import springboot.springsecurity_OAuth2.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class SelfCertification {

    private final UserRepository userRepository;
    public void checkCertification(ProviderUser providerUser) {
        User user = userRepository.findByUsername(providerUser.getId());
//        if(user != null) {
        boolean bool = providerUser.getProvider().equals("none") || providerUser.getProvider().equals("naver");
        providerUser.isCertificated(bool);
//        }
    }

    public void certificate(ProviderUser providerUser) {

    }
}

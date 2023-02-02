package springboot.springsecurity_OAuth2.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.springsecurity_OAuth2.model.users.ProviderUser;
import springboot.springsecurity_OAuth2.model.users.User;
import springboot.springsecurity_OAuth2.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void register(String registrationId, ProviderUser providerUser) {

        User user = User.builder().registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .password(providerUser.getPassword())
                .authorities(providerUser.getAuthorities())
                .provider(providerUser.getProvider())
                .email(providerUser.getEmail())
                .picture(providerUser.getPicture())
                .build();

        userRepository.register(user);
    }
}

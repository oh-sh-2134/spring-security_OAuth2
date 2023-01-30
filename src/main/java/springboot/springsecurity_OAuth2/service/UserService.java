package springboot.springsecurity_OAuth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.springsecurity_OAuth2.model.ProviderUser;
import springboot.springsecurity_OAuth2.model.User;
import springboot.springsecurity_OAuth2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {

        User user = User.builder().registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .provider(providerUser.getPassword())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .build();

        userRepository.register(user);
    }
}

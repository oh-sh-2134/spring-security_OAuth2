package springboot.springsecurity_OAuth2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@Controller
public class IndexController {

//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;

//    @GetMapping("/")
    public String idxController() {
//        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
//        String clientId = clientRegistration.getClientId();
//        System.out.println("clientId = " + clientId);
        return "index";
    }
}

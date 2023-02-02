package springboot.springsecurity_OAuth2.common.enums;

public class OAuth2Config {
    public enum SocialType{
        GOOGLE("google"),
        APPLE("apple"),
        FACEBOOK("facebook"),
        NAVER("naver"),
        KAKAO("kakao");
        private final String socialName;

        SocialType(String socialName) {
            this.socialName = socialName;
        }

        public String getSocialName() {
            return socialName;
        }
    }
}

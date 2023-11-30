package com.example.junglebook.config.oauth;

import com.example.junglebook.config.auth.PrincipalDetails;
import com.example.junglebook.config.oauth.provider.FacebookUserInfo;
import com.example.junglebook.config.oauth.provider.GoogleUserInfo;
import com.example.junglebook.config.oauth.provider.NaverUserInfo;
import com.example.junglebook.config.oauth.provider.OAuth2UserInfo;
import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //후처리되는 함수 -> 구글로부터 받은 userRequest data에 대한 후처리
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: "+userRequest.getClientRegistration()); //registrationId로 어떤 OAuth
        System.out.println("getAccessToken: "+userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code return (OAuth - Client lib) -> AccessToken 요청
        //userRequest information -> loadUser function call -> get a profile on Google
        System.out.println("getAttributes: "+oAuth2User);

        //회원가입 강제 진행
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("Request google login");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            System.out.println("Request facebook login");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }
        else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("Request naver login");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }
        else {
            System.out.println("We only provide google, facebook and naver.");
        }
        String provider = oAuth2UserInfo.getProvider(); //google
        String providerId = oAuth2UserInfo.getProviderId();
        //String username = provider+"_"+providerId; //google_117444705554524627270
        String username = oAuth2UserInfo.getUsername();
        String password = passwordEncoder.encode("겟인데어");
        //String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";
        //해당 아이디로 가입이 되어있는지 확인
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            System.out.println("Your First OAuth2 Login - PrincipalOauth2UserService");
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .userRole(UserRole.USER)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }
        else {
            System.out.println("you already Login ");
        }


        return oAuth2User;
    }
}

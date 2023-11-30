package com.example.junglebook.config.oauth.provider;

import java.security.Provider;

public interface OAuth2UserInfo {

    String getProviderId(); //구글, 페이스북, 네이버, 카카오톡 등의 아이디
    String getProvider(); //구글, 페이스북, 네이버, 카카오톡 등

    String getUsername(); //이메일

    String getName(); //이름
}

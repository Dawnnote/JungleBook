package com.example.junglebook.config.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; //OAuth2User가 가지고 있는 getAttributes

    public FacebookUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getUsername() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}

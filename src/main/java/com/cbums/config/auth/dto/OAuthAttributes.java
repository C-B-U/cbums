package com.cbums.config.auth.dto;

import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.UserRoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private AuthProvider provider;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String picture, AuthProvider provider) {
        this.attributes = attributes;
        this.nameAttributeKey= nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.provider = provider;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .provider(AuthProvider.GOOGLE)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(UserRoleType.GUEST)
                .provider(provider)
                .build();
    }
}
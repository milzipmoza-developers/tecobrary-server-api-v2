package com.woowacourse.tecobrary.security;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@ToString
public class GithubUserDto implements OAuth2User, Serializable {

    private String name;
    private String email;
    private String picture;
    private List<String> roles;

    @Builder
    public GithubUserDto(String name, String email, String picture, String role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.roles = Collections.singletonList(role);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getAttributes() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.name);
        map.put("email", this.email);
        map.put("picture", this.picture);
        map.put("roles", this.roles);
        return map;
    }
}

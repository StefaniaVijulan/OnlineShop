package com.example.onlineshop.security.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class Designer implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String firstNameDesigner;

    @Column(name = "lastName")
    private String lastNameDesigner;

    @Column(name = "passwordDesigner")
    private String passwordDesigner;

    @Column(name = "phoneDesigner")
    private String phoneDesigner;

    @Column(name = "facebookDesigner")
    private String facebookDesigner;

    @Column(name = "linkedinDesigner")
    private String linkedinDesigner;

    @Column(name = "experienceDesigner")
    private Integer experienceDesigner;

    @Column(name = "detailDesigner")
    private String detailDesigner;

    @Column(name = "imageDesigner")
    private String imageDesigner;

    @Column(name = "role")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getPasswordDesigner() ;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

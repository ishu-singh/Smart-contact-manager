package com.scm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false, unique = true, length = 36)
    private String userId = UUID.randomUUID().toString();
    
    @Column(name="user_name",nullable=false)
    private String userName;
    @Getter(value = AccessLevel.NONE)
    private String userPassword;
    @Column(unique=true,nullable=false)
    private String userEmail;
    @Column(length=5000)
    private String about;
    @Column(length=5000)
    private String profilePic;
    private String phoneNumber;
    //other information
    @Getter(value = AccessLevel.NONE)
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //Self,google,facebook,github(how he signed up)
    @Enumerated(value=EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerId;

    //add more fields if needed
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
    private List<Contact> contacts=new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> rolesList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //list of roles[USER,ADMIN] has been converted to list of SimpleGrantedAuthority
        Collection<SimpleGrantedAuthority> roles= rolesList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }
    // for this project:
    // email id --> username

    @Override
    public String getUsername() {
        return this.userEmail;
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
        return this.enabled;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

  

}

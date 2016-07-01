package com.security.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users", schema = "test", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date lastPasswordReset=new Date();
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAccountNonExpired=true;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAccountNonLocked=true;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isCredentialsNonExpired=true;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled=true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    private Collection<UserRole> authorities;

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String password,String email,
                boolean isAccountNonExpired,boolean isCredentialsNonExpired,boolean isAccountNonLocked,
                boolean enabled, Set<UserRole> authorities) {
        this.username = username;
        this.password = password;
        this.email=email;
        this.isAccountNonExpired=isAccountNonExpired;
        this.isCredentialsNonExpired=isCredentialsNonExpired;
        this.isAccountNonLocked=isAccountNonLocked;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public Collection<UserRole> getAuthorities() {
       return authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setAuthorities(Collection<UserRole> authorities) {
        this.authorities = authorities;
    }
}
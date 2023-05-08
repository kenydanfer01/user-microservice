package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PrincipalUser implements UserDetails {
    private String nombreUsuario;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(
            String dni, String name, String surname, String mail,
            String password, Collection<? extends GrantedAuthority> authorities
    ) {
        this.nombreUsuario = dni;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalUser build(UserEntity usuario, RoleEntity role) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role.getName()));

        return new PrincipalUser(
                usuario.getDni(), usuario.getName(), usuario.getSurname(),
                usuario.getMail(), usuario.getPassword() ,authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }
}

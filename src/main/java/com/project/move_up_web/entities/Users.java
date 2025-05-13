package com.project.move_up_web.entities;

import com.project.move_up_web.configs.RolesEnum;
import com.project.move_up_web.entities.shared.PrimaryId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users extends PrimaryId implements UserDetails {
    @Column(name = "userId")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "dateOfBirth")
    private LocalDateTime dateOfBirth;
    @Column(name = "roleName")
    private String roleName;
    @Column(name = "status")
    private String status;

    @Transient
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return "Active".equalsIgnoreCase(this.status);
    }
}

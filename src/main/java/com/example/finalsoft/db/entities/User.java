package com.example.finalsoft.db.entities;

import com.example.finalsoft.db.enums.RoleEnum;
import com.example.finalsoft.db.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    String firstName;
    String lastName;
    @Enumerated(EnumType.STRING)
    RoleEnum role;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    Instant createdAt;
    Instant updatedAt;
    Instant blockedAt;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    List<F1Team> teams;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Enrollment> enrollments;


    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
        status = StatusEnum.ACTIVE;
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return status == StatusEnum.ACTIVE;
    }
}

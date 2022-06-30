package com.travellers.community.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Table(name = "user",schema = "public")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User implements Serializable {

    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "first_name",nullable = false)
    private String first_name;

    @Column(name = "last_name",nullable = false)
    private String last_name;

    @Column(name = "nic",nullable = false)
    private String nic;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private Role role;

    public User() {
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "user_id=" + user_id +
//                ", username='" + username + '\'' +
//                ", roles=" + role +
//                '}';
//    }

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role")
//    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "u_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id", referencedColumnName = "role_id")})
    private List<Role> roles;

//    public User merge(User updateRequest) {
//        Optional.ofNullable(updateRequest.getUsername()).ifPresent(this::setUsername);
//        Optional.ofNullable(updateRequest.getPassword()).ifPresent(this::setPassword);
//        Optional.ofNullable(updateRequest.getRole()).ifPresent(this::setRole);
//
//        return this;
//    }
//
//    public enum Role implements GrantedAuthority {
//        NORMAL,
//        ADMIN;
//
//        @Override
//        public String getAuthority() {
//            return "ROLE_" + this.name();
//        }
//    }

}

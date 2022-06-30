package com.travellers.community.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Table(name = "role",schema = "public")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@EntityListeners(AuditingEntityListener.class)
public class Role implements Serializable {

    @Id
    @Column(name = "role_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(name = "role",nullable = false,unique = true)
    private String role;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private boolean enabled;

//    @ManyToMany(mappedBy = "roles")
//    @JsonIgnore
//    private List<User> users;

    @ManyToMany(mappedBy = "roles", fetch = LAZY)
    @JsonIgnore
    private List<User> users;

//    @Override
//    public String toString(){
//        return "Role{"+
//                "roleId"+roleId+
//                ",role='"+role+'\''+
//                '}';
//    }


    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role='" + role + '\'' +
                '}';
    }
}

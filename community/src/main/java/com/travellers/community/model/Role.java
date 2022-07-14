package com.travellers.community.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "role",schema = "public")
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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role='" + role + '\'' +
                '}';
    }
}

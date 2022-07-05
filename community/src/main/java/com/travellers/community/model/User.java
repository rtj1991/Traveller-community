package com.travellers.community.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "user",schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "profile_pic")
    private String profile_pic;

    @Column(name = "dob",nullable = false)
    private String dob;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    public User() {
    }

//    one-to-many relation between gallary
    @OneToMany(mappedBy = "userId")
    @JsonBackReference
    private List<Gallery> galley;

    //    one-to-many relation between visit places
    @OneToMany(mappedBy = "userId")
    @JsonBackReference
    private List<MyTrip> trips;

    //    one-to-many relation between review
    @OneToMany(mappedBy = "userId")
    @JsonBackReference
    private List<Review> sender;

    //    one-to-many relation between review
    @OneToMany(mappedBy = "reviewer")
    @JsonBackReference
    private List<Review> review;

    //    one-to-many relation between message
    @OneToMany(mappedBy = "userId")
    @JsonBackReference
    private List<Message> messages;

    //    one-to-many relation between follower
    @OneToMany(mappedBy = "follower")
    @JsonBackReference
    private List<Follower> follower;

    //    one-to-many relation between follower
    @OneToMany(mappedBy = "followedby")
    @JsonBackReference
    private List<Follower> followedby;

//    Many-to-many relation between role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "u_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id", referencedColumnName = "role_id")})
    private List<Role> roles;

}

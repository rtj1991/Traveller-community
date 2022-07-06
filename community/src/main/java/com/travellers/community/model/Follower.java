package com.travellers.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "follower",schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower")
    @JsonIgnore
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followedby")
    @JsonIgnore
    private User followedby;

    @Column(name = "status")
    private int status;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;
}

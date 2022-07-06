package com.travellers.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "message",schema = "public")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver")
    @JsonIgnore
    private User receiver;

    //    Many-to-One relation between user
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender")
    @JsonIgnore
    private User sender;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;
}

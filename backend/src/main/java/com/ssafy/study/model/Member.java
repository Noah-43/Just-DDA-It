package com.ssafy.study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userName")
    private String userName;
    
    @Column(name="userContent")
    private String userContent;

    @Column(name="password")
    private String password;

    @Column(name="userThumbnail", columnDefinition="BLOB")
    @Lob
    private byte[] userThumbnail;

    @Column(name="major")
    private String major;

    @Column(name="education")
    private String education;

    @Column(name="field1")
    private String field1;

    @Column(name="desiredField1")
    private String desiredField1;
    
    @Column(name="desiredField2")
    private String desiredField2;
    
    @Column(name="desiredField3")
    private String desiredField3;
    
    @Column(name = "isSecret")
    private boolean isSecret;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Set<DateForUser> dateForUsers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Set<Feed> feeds;


    /*

     */
    protected Set<DateForUser> getDateForUsersInternal(){
        if(this.dateForUsers==null){
            this.dateForUsers = new HashSet<>();
        }
        return this.dateForUsers;
    }
    protected void setDateForUsersInternal(Set<DateForUser> dateForUsers){
        this.dateForUsers=dateForUsers;
    }

    public void addDateForUser(DateForUser dateForUsers){
        getDateForUsersInternal().add(dateForUsers);
        dateForUsers.setMember(this);
    }

    /*

     */
    protected Set<Feed> getFeedsInternal(){
        if(this.feeds==null){
            this.feeds = new HashSet<>();
        }
        return this.feeds;
    }
    protected void setFeedsInternal(Set<Feed> feeds){
        this.feeds=feeds;
    }

    public void addFeed(Feed feeds){
        getFeedsInternal().add(feeds);
        feeds.setMember(this);
    }

    public Follow follow(Member target){return new Follow(this,target); }
    
}

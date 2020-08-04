package com.ssafy.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="studyrooms")
public class Studyroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "license_id")
    private License license;

    @Column(name = "captainId")
    private Long captainId;

    @Column(name = "roomTitle")
    private String roomTitle;

    @Column(name = "testDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testDate;

    @Column(name="roomDate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roomDate;

    @Column(name = "isPrivate")
    private boolean isPrivate;

    @Column(name = "roomPassword")
    private String roomPassword;

    @Column(name = "roomInfo")
    private String roomInfo;

    @Column(name = "roomGoal")
    private String roomGoal;

    @Column(name = "maxMembers")
    private int maxMembers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyroom")
    private Set<Hashtag> roomHashtag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyroom")
    private Set<DateForStudyroom> dateForStudyrooms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyroom")
    private Set<Feed> feeds;

    protected Set<Hashtag> getHashtagInternal(){
        if(this.roomHashtag==null){
            this.roomHashtag = new HashSet<>();
        }
        return this.roomHashtag;
    }
    protected void setHashtagInternal(Set<Hashtag> roomHashtag){
        this.roomHashtag=roomHashtag;
    }

    public void addReview(Hashtag roomHashtag){
        getHashtagInternal().add(roomHashtag);
        roomHashtag.setStudyroom(this);
    }

    /*

     */

    /*

     */
    protected Set<DateForStudyroom> getDateForStudyroomsInternal(){
        if(this.dateForStudyrooms==null){
            this.dateForStudyrooms = new HashSet<>();
        }
        return this.dateForStudyrooms;
    }
    protected void setDateForStudyroomsInternal(Set<DateForStudyroom> dateForStudyrooms){
        this.dateForStudyrooms=dateForStudyrooms;
    }

    public void addDateForStudyroom(DateForStudyroom dateForStudyrooms){
        getDateForStudyroomsInternal().add(dateForStudyrooms);
        dateForStudyrooms.setStudyroom(this);
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
        feeds.setStudyroom(this);
    }
}
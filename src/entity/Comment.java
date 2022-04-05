/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author wassim
 */
public class Comment {
    private int id ; 
    private int event_id ; 
    private Date commentdate; 
    private int likecount;
    private String commentcontent ; 
    private int user_id ; 

    public Comment() {
    }

    public Comment(int id, int event_id, Date commentdate, int likecount, String commentcontent, int user_id) {
        this.id = id;
        this.event_id = event_id;
        this.commentdate = commentdate;
        this.likecount = likecount;
        this.commentcontent = commentcontent;
        this.user_id = user_id;
    }

    public Comment(int id, int event_id, Date commentdate, String commentcontent, int user_id) {
        this.id = id;
        this.event_id = event_id;
        this.commentdate = commentdate;
        this.commentcontent = commentcontent;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", event_id=" + event_id + ", commentdate=" + commentdate + ", likecount=" + likecount + ", commentcontent=" + commentcontent + ", user_id=" + user_id + '}';
    }
    
}

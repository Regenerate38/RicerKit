package com.example.ricerkit.Models;

public class CommentModel {

    private String commentuserimage;
    private String commentusername;
    private String commentuplaodtime;
    private String commentdetails;

    public CommentModel(String commentuserimage, String commentusername, String commentuplaodtime, String commentdetails) {
        this.commentuserimage = commentuserimage;
        this.commentusername = commentusername;
        this.commentuplaodtime = commentuplaodtime;
        this.commentdetails = commentdetails;
    }

    public String getCommentuserimage() {
        return commentuserimage;
    }

    public void setCommentuserimage(String commentuserimage) {
        this.commentuserimage = commentuserimage;
    }

    public String getCommentusername() {
        return commentusername;
    }

    public void setCommentusername(String commentusername) {
        this.commentusername = commentusername;
    }

    public String getCommentuplaodtime() {
        return commentuplaodtime;
    }

    public void setCommentuplaodtime(String commentuplaodtime) {
        this.commentuplaodtime = commentuplaodtime;
    }

    public String getCommentdetails() {
        return commentdetails;
    }

    public void setCommentdetails(String commentdetails) {
        this.commentdetails = commentdetails;
    }
}

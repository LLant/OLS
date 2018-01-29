package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import java.util.Date;

public class Comment extends BaseBean{

    private Integer commentId;
    private Integer coursewareId;
    private String commentContent;
    private Date commentDate;
    private String isReply;
    private Integer replyGoal;
    private Integer commentauthor;
    private Long star;              //点赞次数

    public Comment(){}

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Integer coursewareId) {
        this.coursewareId = coursewareId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getIsReply() {
        return isReply;
    }

    public void setIsReply(String isReply) {
        this.isReply = isReply;
    }

    public Integer getReplyGoal() {
        return replyGoal;
    }

    public void setReplyGoal(Integer replyGoal) {
        this.replyGoal = replyGoal;
    }

    public Integer getCommentauthor() {
        return commentauthor;
    }

    public void setCommentauthor(Integer commentauthor) {
        this.commentauthor = commentauthor;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }
}

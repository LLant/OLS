package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

public class Comment extends BaseBean{

    @Id
    private Integer id;
    private Integer coursewareId;
    private String commentContent;
    private Date commentDate;
    private Integer commentAuthor;
    private Long star;              //点赞次数
    private String replyContent;
    private String isRead;
    private String adminRead;

    public Comment(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(Integer commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getAdminRead() {
        return adminRead;
    }

    public void setAdminRead(String adminRead) {
        this.adminRead = adminRead;
    }
}

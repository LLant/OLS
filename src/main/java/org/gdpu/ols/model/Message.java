package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

public class Message extends BaseBean {

    @Id
    private Integer id;
    private Date date;
    private String content;
    private Integer targetUser;
    private String isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Integer targetUser) {
        this.targetUser = targetUser;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}

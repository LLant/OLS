package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import java.util.Date;

public class Courseware extends BaseBean {

    private Integer coursewareId;
    private Integer author;
    private Integer examiner;       //课件审查人
    private String coursewareName;
    private Date coursewarePublishDate;
    private String coursewareStatus;

    public Courseware(){}

    public Integer getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Integer coursewareId) {
        this.coursewareId = coursewareId;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getExaminer() {
        return examiner;
    }

    public void setExaminer(Integer examiner) {
        this.examiner = examiner;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public Date getCoursewarePublishDate() {
        return coursewarePublishDate;
    }

    public void setCoursewarePublishDate(Date coursewarePublishDate) {
        this.coursewarePublishDate = coursewarePublishDate;
    }

    public String getCoursewareStatus() {
        return coursewareStatus;
    }

    public void setCoursewareStatus(String coursewareStatus) {
        this.coursewareStatus = coursewareStatus;
    }
}

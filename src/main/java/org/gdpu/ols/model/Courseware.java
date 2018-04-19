package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

public class Courseware extends BaseBean {

    @Id
    private Integer id;
    private Integer author;
    private Integer examiner;       //课件审查人
    private String coursewareName;
    private Date coursewarePublishDate;
    private String coursewareStatus;
    private String coursewareIntroduction;      //课程简介
    private String coursewareTip;               //课程须知
    private String coursewareContent;           //课程内容
    private Integer previousCourseware;
    private Integer nextCourseware;
    private Integer coursewareType;
    private String coursewarePhotoLocation;
    private String recommend;

    public Courseware(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCoursewareIntroduction() {
        return coursewareIntroduction;
    }

    public void setCoursewareIntroduction(String coursewareIntroduction) {
        this.coursewareIntroduction = coursewareIntroduction;
    }

    public String getCoursewareTip() {
        return coursewareTip;
    }

    public void setCoursewareTip(String coursewareTip) {
        this.coursewareTip = coursewareTip;
    }

    public String getCoursewareContent() {
        return coursewareContent;
    }

    public void setCoursewareContent(String coursewareContent) {
        this.coursewareContent = coursewareContent;
    }

    public Integer getPreviousCourseware() {
        return previousCourseware;
    }

    public void setPreviousCourseware(Integer previousCourseware) {
        this.previousCourseware = previousCourseware;
    }

    public Integer getNextCourseware() {
        return nextCourseware;
    }

    public void setNextCourseware(Integer nextCourseware) {
        this.nextCourseware = nextCourseware;
    }

    public Integer getCoursewareType() {
        return coursewareType;
    }

    public void setCoursewareType(Integer coursewareType) {
        this.coursewareType = coursewareType;
    }

    public String getCoursewarePhotoLocation() {
        return coursewarePhotoLocation;
    }

    public void setCoursewarePhotoLocation(String coursewarePhotoLocation) {
        this.coursewarePhotoLocation = coursewarePhotoLocation;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}

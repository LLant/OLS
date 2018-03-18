package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

public class ViewCoursewareDetail extends BaseBean{

    @Id
    private Integer id;
    private Integer author;
    private String coursewareContent;
    private String coursewareName;
    private String coursewareIntroduction;
    private Date coursewarePublishDate;
    private String coursewareStatus;
    private String coursewarePhotoLocation;
    private String coursewareTip;
    private String aspect;
    private String category;

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

    public String getCoursewareContent() {
        return coursewareContent;
    }

    public void setCoursewareContent(String coursewareContent) {
        this.coursewareContent = coursewareContent;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getCoursewareIntroduction() {
        return coursewareIntroduction;
    }

    public void setCoursewareIntroduction(String coursewareIntroduction) {
        this.coursewareIntroduction = coursewareIntroduction;
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

    public String getCoursewarePhotoLocation() {
        return coursewarePhotoLocation;
    }

    public void setCoursewarePhotoLocation(String coursewarePhotoLocation) {
        this.coursewarePhotoLocation = coursewarePhotoLocation;
    }

    public String getCoursewareTip() {
        return coursewareTip;
    }

    public void setCoursewareTip(String coursewareTip) {
        this.coursewareTip = coursewareTip;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ViewCoursewareDetail{" +
                "id=" + id +
                ", author=" + author +
                ", coursewareContent='" + coursewareContent + '\'' +
                ", coursewareName='" + coursewareName + '\'' +
                ", coursewareIntroduction='" + coursewareIntroduction + '\'' +
                ", coursewarePublishDate=" + coursewarePublishDate +
                ", coursewareStatus='" + coursewareStatus + '\'' +
                ", coursewarePhotoLocation='" + coursewarePhotoLocation + '\'' +
                ", coursewareTip='" + coursewareTip + '\'' +
                ", aspect='" + aspect + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

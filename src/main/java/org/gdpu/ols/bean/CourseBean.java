package org.gdpu.ols.bean;

import org.gdpu.ols.common.BaseBean;

import java.util.Date;
import java.util.List;

public class CourseBean extends BaseBean{

    private Integer id;
    private Integer author;
    private String authorRealName;
    private String authorPhotoLocation;
    private String authorDegree;
    private String coursewareName;
    private String coursewareContent;
    private Date coursewarePublishDate;
    private List<String> coursewareIntroduction;
    private List<String> coursewareTip;
    private String videoLocation;
    private String attachLocation;
    private String photoLocation;

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

    public String getAuthorDegree() {
        return authorDegree;
    }

    public void setAuthorDegree(String authorDegree) {
        this.authorDegree = authorDegree;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }

    public String getAttachLocation() {
        return attachLocation;
    }

    public void setAttachLocation(String attachLocation) {
        this.attachLocation = attachLocation;
    }

    public List<String> getCoursewareIntroduction() {
        return coursewareIntroduction;
    }

    public void setCoursewareIntroduction(List<String> coursewareIntroduction) {
        this.coursewareIntroduction = coursewareIntroduction;
    }

    public List<String> getCoursewareTip() {
        return coursewareTip;
    }

    public void setCoursewareTip(List<String> coursewareTip) {
        this.coursewareTip = coursewareTip;
    }

    public String getCoursewareContent() {
        return coursewareContent;
    }

    public void setCoursewareContent(String coursewareContent) {
        this.coursewareContent = coursewareContent;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public String getAuthorRealName() {
        return authorRealName;
    }

    public void setAuthorRealName(String authorRealName) {
        this.authorRealName = authorRealName;
    }

    public String getAuthorPhotoLocation() {
        return authorPhotoLocation;
    }

    public void setAuthorPhotoLocation(String authorPhotoLocation) {
        this.authorPhotoLocation = authorPhotoLocation;
    }
}

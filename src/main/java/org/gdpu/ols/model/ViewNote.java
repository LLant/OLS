package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

public class ViewNote extends BaseBean {

    @Id
    private Integer id;
    private Integer user;
    private String noteContent;
    private Date noteDate;
    private String coursewareName;
    private String studentName;
    private String photoStorageLocation;
    private Integer coursewareId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhotoStorageLocation() {
        return photoStorageLocation;
    }

    public void setPhotoStorageLocation(String photoStorageLocation) {
        this.photoStorageLocation = photoStorageLocation;
    }

    public Integer getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Integer coursewareId) {
        this.coursewareId = coursewareId;
    }
}

package org.gdpu.ols.bean;

public class StudentCustom {

    private Integer studentId;
    private String studentName;
    private String studentPassword;
    private String studentEmail;
    private String studentSex;
    private String photoStorageLocation;
    private Integer learningTime;
    private Integer starNumber;

    public StudentCustom(){}

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public Integer getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(Integer learningTime) {
        this.learningTime = learningTime;
    }

    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }

    public String getPhotoStorageLocation() {
        return photoStorageLocation;
    }

    public void setPhotoStorageLocation(String photoStorageLocation) {
        this.photoStorageLocation = photoStorageLocation;
    }
}

package org.gdpu.ols.model;

import org.apache.ibatis.type.Alias;
import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;


@Alias("student")                   //使用alias标签来指定名称
public class Student extends BaseBean {

    @Id
    private Integer id;
    private String studentName;
    private transient String studentPassword;
    private String studentEmail;
    private String studentSex;
    private String photoStorageLocation;         //图片存储位置
    private String status;
    private Integer teacherId;

    public Student(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhotoStorageLocation() {
        return photoStorageLocation;
    }

    public void setPhotoStorageLocation(String photoStorageLocation) {
        this.photoStorageLocation = photoStorageLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", photoStorageLocation='" + photoStorageLocation + '\'' +
                ", status='" + status + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}

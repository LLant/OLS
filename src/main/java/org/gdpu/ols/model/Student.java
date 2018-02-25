package org.gdpu.ols.model;

import org.apache.ibatis.type.Alias;
import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;


@Alias("student")                   //使用alias标签来指定名称
public class Student extends BaseBean {

    @Id
    private Integer studentId;
    private String studentName;
    private transient String studentPassword;
    private String studentEmail;
    private String studentSex;

    public Student(){}

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

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studnetPassword='" + studentPassword + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentSex='" + studentSex + '\'' +
                '}';
    }
}

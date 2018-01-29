package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

public class RoleRelationship extends BaseBean {

    private Integer student;
    private Integer teacher;

    public RoleRelationship(){}


    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }
}

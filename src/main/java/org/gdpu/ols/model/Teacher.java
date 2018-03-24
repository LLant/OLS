package org.gdpu.ols.model;

import org.apache.ibatis.type.Alias;
import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;


@Alias("teacher")
public class Teacher extends BaseBean{

    @Id
    private Integer id;
    private String realName;
    private String degree;
    private String university;
    private String selfIntroduction;
    private String photoStorageLocation;

    public Teacher(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getPhotoStorageLocation() {
        return photoStorageLocation;
    }

    public void setPhotoStorageLocation(String photoStorageLocation) {
        this.photoStorageLocation = photoStorageLocation;
    }
}

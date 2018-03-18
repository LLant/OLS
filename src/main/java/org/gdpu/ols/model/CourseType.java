package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;

public class CourseType extends BaseBean{

    @Id
    private Integer id;
    private String aspect;
    private String category;

    public CourseType(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

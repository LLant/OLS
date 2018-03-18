package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.CourseTypeMapper;
import org.gdpu.ols.mapper.FileMapper;
import org.gdpu.ols.model.CourseType;
import org.gdpu.ols.service.CourseTypeService;
import org.gdpu.ols.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseTypeServiceImpl extends AbstractService<CourseType> implements CourseTypeService{

    @Resource
    private CourseTypeMapper courseTypeMapper;

}

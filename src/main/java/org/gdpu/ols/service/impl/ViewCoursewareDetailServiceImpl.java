package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.CoursewareMapper;
import org.gdpu.ols.model.Courseware;
import org.gdpu.ols.model.ViewCoursewareDetail;
import org.gdpu.ols.service.CoursewareService;
import org.gdpu.ols.service.ViewCoursewareDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ViewCoursewareDetailServiceImpl extends AbstractService<ViewCoursewareDetail> implements ViewCoursewareDetailService {
}

package org.gdpu.ols.mapper;

import org.gdpu.ols.model.ViewCoursewareDetail;

import java.util.List;

public interface ViewCoursewareDetailMapper extends org.gdpu.ols.core.Mapper<ViewCoursewareDetail> {

    List<ViewCoursewareDetail> getTop5();
}

package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Courseware;
import org.gdpu.ols.model.ViewCoursewareDetail;

import java.util.List;

public interface ViewCoursewareDetailService extends Service<ViewCoursewareDetail> {
    List<ViewCoursewareDetail> getTop5();
}

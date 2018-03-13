package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Courseware;

public interface CoursewareService extends Service<Courseware> {
    int addSingleCourseware(String coursewareName,String coursewareIntroduction,String coursewareTip,
                            Integer author);
}

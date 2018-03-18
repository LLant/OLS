package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Courseware;

public interface CoursewareService extends Service<Courseware> {
    Courseware addSingleCourseware(String coursewareName,String coursewareIntroduction,String coursewareTip,
                            String coursewareStatus,Integer author,String courseType,String coursewarePhotoLocation);
}
